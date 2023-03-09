package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.IngredientDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.FoodGroupDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.FoodGroupRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.IngredientRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.RecipeRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class RecipeService extends AbstractService{

    Logger logger = Logger.getLogger(RecipeService.class.getName());

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FoodGroupRepository foodGroupRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    public List<RecipeDto> getAllRecipes(){
        long startTime = System.nanoTime();
        List<RecipeDto> dtoList = new ArrayList<>();
        recipeRepository.findAll().forEach(recipeDao ->  dtoList.add(new RecipeDto(recipeDao)));
        long stopTime = System.nanoTime();
        logger.info("Recipes query: "+((stopTime-startTime)/1000000)+" ms");
        return dtoList;
    }

    public List<RecipeDto> getUserRecipes(Long userId){
        long startTime = System.nanoTime();
        List<RecipeDto> recipes = new ArrayList<>();
        Optional<UserDao> userDao = userRepository.findById(userId);
        if(userDao.isPresent()){
            List<RecipeDao> recipeDaos = recipeRepository.findAllByCreator(userDao.get());
            if(!recipeDaos.isEmpty()){
                recipeDaos.forEach(recipe -> recipes.add(new RecipeDto(recipe)));
            }
        }else{
            logger.severe("Error on finding user by id: "+ userId);
            return null;
        }

        long stopTime = System.nanoTime();
        logger.info("Recipes query: "+((stopTime-startTime)/1000000)+" ms");
        return recipes;
    }

    public List<RecipeDto> getFoodGroupRecipes(FoodGroupDto dto){
        long startTime = System.nanoTime();
        List<RecipeDto> recipes = new ArrayList<>();
        FoodGroupDao dao = new FoodGroupDao(dto);
        List<RecipeDao> recipeDaos = recipeRepository.findAllByFoodGroupsContains(dao);
        if(!recipeDaos.isEmpty()){
           recipeDaos.forEach(recipe -> recipes.add(new RecipeDto(recipe)));
        }
        long stopTime = System.nanoTime();
        logger.info("Recipes query: "+((stopTime-startTime)/1000000)+" ms");
        return recipes;
    }

    @Transactional
    public RecipeDto createRecipe(RecipeDto dto) {
        long startTime = System.nanoTime();

        RecipeDto returnDto;
        Optional<UserDao> userDao = userRepository.findById(dto.getCreatorId());
        if (userDao.isPresent()) {
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
                    .creator(userDao.get())
                    .image(dto.getImage())
                    .instruction(dto.getInstruction())
                    .ingredients(ingredients)
                    .foodGroups(foodGroups)
                    .build();
            RecipeDao savedRecipeDao = recipeRepository.save(recipeDao);
            returnDto = new RecipeDto(savedRecipeDao);
        }else{
            logger.severe("Error on finding user by id: "+ dto.getCreatorId());
            return null;
        }

        long stopTime = System.nanoTime();
        logger.info("Recipe creation: "+((stopTime-startTime)/1000000)+" ms");

        return returnDto;
    };


    public RecipeDto getRecipeById(Long id){
        Optional<RecipeDao> dao = recipeRepository.findById(id);

        if(dao.isPresent()) {
            return new RecipeDto(dao.get());
        }else {
            logger.severe("Recipe not found - id:" + id);
            return null;
        }
    }

    public RecipeDto modifyRecipeById(RecipeDto recipeDto) {
        Optional<RecipeDao> recipeDao = recipeRepository.findById(recipeDto.getId());
        Optional<UserDao> userDao = userRepository.findById(recipeDto.getCreatorId());
        if(recipeDao.isPresent() && userDao.isPresent()){
            try{
                recipeDao.get().modify(recipeDto, userDao.get());
                RecipeDao modifiedDao = recipeRepository.save(recipeDao.get());
                RecipeDto returnDto = new RecipeDto(modifiedDao);
                return returnDto;
            } catch (Throwable err){
                logger.severe("Error on recipe modification");
                return null;
            }
        }
        logger.severe("User ("+recipeDto.getCreatorId()+") or Recipe ("+recipeDto.getId()+") are invalid");
        return null;
    }
}
