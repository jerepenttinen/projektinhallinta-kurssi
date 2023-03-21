package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.Dao.IngredientDao;
import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.FoodGroupRepository;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.IngredientRepository;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.RecipeRepository;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.UserRepository;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final IngredientRepository ingredientRepository;

    public List<RecipeDao> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<RecipeDao> getUserRecipes(Long userId) throws UserNotFoundException {
        UserDao user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return recipeRepository.findAllByCreator(user);
    }

//    public List<RecipeDao> getFoodGroupRecipes(String foodGroupId) {
//        return recipeRepository.findAllByFoodGroupsContains(foodGroupRepository.findById(foodGroupId));
//    }

    @Transactional
    public RecipeDto createRecipe(RecipeDto dto, UserDao userDao) {
        long startTime = System.nanoTime();

        var ingredients = dto.getIngredients().stream().map(i -> {
            var ingredient = ingredientRepository.findFirstByName(i.getName());
            if (ingredient != null) {
                return ingredient;
            }
            return ingredientRepository.save(IngredientDao.builder().name(i.getName()).build());
        }).collect(Collectors.toSet());

        var foodGroups = dto.getFoodGroups().stream().map(i -> {
            var ingredient = foodGroupRepository.findFirstByName(i.getName());
            if (ingredient != null) {
                return ingredient;
            }
            return foodGroupRepository.save(FoodGroupDao.builder().name(i.getName()).build());
        }).collect(Collectors.toSet());

        var recipeDao = RecipeDao.builder()
                .recipeName(dto.getRecipeName())
                .creator(userDao)
                .image(dto.getImage())
                .instruction(dto.getInstruction())
                .ingredients(ingredients)
                .foodGroups(foodGroups)
                .build();
        RecipeDao savedRecipeDao = recipeRepository.save(recipeDao);
        var returnDto = new RecipeDto(savedRecipeDao);

        long stopTime = System.nanoTime();
        log.info("Recipe creation: " + ((stopTime - startTime) / 1000000) + " ms");

        return returnDto;
    }


    public RecipeDao getRecipeById(Long id) throws RecipeNotFoundException {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public RecipeDto modifyRecipeById(RecipeDto recipeDto) {
        Optional<RecipeDao> recipeDao = recipeRepository.findById(recipeDto.getId());
        Optional<UserDao> userDao = userRepository.findById(recipeDto.getCreatorId());
        if (recipeDao.isPresent() && userDao.isPresent()) {
            try {
                recipeDao.get().modify(recipeDto, userDao.get());
                RecipeDao modifiedDao = recipeRepository.save(recipeDao.get());
                return new RecipeDto(modifiedDao);
            } catch (Throwable err) {
                log.error("Error on recipe modification");
                return null;
            }
        }
        log.error("User (" + recipeDto.getCreatorId() + ") or Recipe (" + recipeDto.getId() + ") are invalid");
        return null;
    }
}
