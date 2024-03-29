package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Controller.Request.CreateRecipeRequest;
import com.tamkstudents.cookbook.Domain.Dao.*;
import com.tamkstudents.cookbook.Domain.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.RepositoryInterface.*;
import com.tamkstudents.cookbook.Service.Exceptions.RecipeNotFoundException;
import com.tamkstudents.cookbook.Service.Exceptions.UnknownFoodGroupException;
import com.tamkstudents.cookbook.Service.Exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;
    private final FoodGroupRepository foodGroupRepository;
    private final IngredientRepository ingredientRepository;
    private final ImageRepository imageRepository;
    private final MediaService mediaService;

    public List<RecipeDao> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<RecipeDao> getUserRecipes(Long userId) throws UserNotFoundException {
        UserDao user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        return recipeRepository.findAllByCreator(user);
    }

    public List<RecipeDao> search(String term) {
        return recipeRepository.findRecipeDaosByRecipeNameLikeIgnoreCase(term);
    }

//    public List<RecipeDao> getFoodGroupRecipes(String foodGroupId) {
//        return recipeRepository.findAllByFoodGroupsContains(foodGroupRepository.findById(foodGroupId));
//    }

    @Transactional(rollbackOn = Exception.class)
    public RecipeDao createRecipe(CreateRecipeRequest createRecipeRequest, UserDao userDao) throws UnknownFoodGroupException {
        var foodGroups = new HashSet<FoodGroupDao>();
        for (var category : createRecipeRequest.getCategories()) {
            var foodGroup = foodGroupRepository.findFirstByName(category);
            if (foodGroup == null) {
                throw new UnknownFoodGroupException(category);
            }
            foodGroups.add(foodGroup);
        }

        var images = new HashSet<>(imageRepository.saveAll(createRecipeRequest
                .getImages()
                .stream()
                .map(mediaService::base64ToImage)
                .map(image -> ImageDao.builder().image(image).build())
                .toList()
        ));

        var recipeDao = RecipeDao.builder()
                .recipeName(createRecipeRequest.getRecipeName())
                .creator(userDao)
                .instruction(createRecipeRequest.getInstructions())
                .ingredients(new HashSet<>())
                .images(images)
                .foodGroups(foodGroups)
                .build();

        createRecipeRequest.getIngredients().forEach(it -> {
            var ingredient = ingredientRepository.findFirstByName(it.getIngredient());
            ingredient = (ingredient != null) ? ingredient : ingredientRepository.save(IngredientDao.builder().name(it.getIngredient()).build());
            recipeDao.addIngredient(ingredient, it.getQuantity());
        });

        return recipeRepository.save(recipeDao);
    }


    public RecipeDao getRecipeById(Long id) throws RecipeNotFoundException {
        return recipeRepository.findById(id).orElseThrow(RecipeNotFoundException::new);
    }

    public RecipeDto modifyRecipeById(RecipeDto recipeDto) {
        Optional<RecipeDao> recipeDao = recipeRepository.findById(recipeDto.getId());
        Optional<UserDao> userDao = userRepository.findById(recipeDto.getCreatorId());
        if (recipeDao.isPresent() && userDao.isPresent()) {
            try {
//                recipeDao.get().modify(recipeDto, userDao.get());
//                RecipeDao modifiedDao = recipeRepository.save(recipeDao.get());
//                return new RecipeDto(modifiedDao);
                throw new RuntimeException("TODO");

            } catch (Throwable err) {
                log.error("Error on recipe modification");
                return null;
            }
        }
        log.error("User (" + recipeDto.getCreatorId() + ") or Recipe (" + recipeDto.getId() + ") are invalid");
        return null;
    }

    public List<FoodGroupDao> getRecipeCategories() {
        return foodGroupRepository.findAll();
    }
}
