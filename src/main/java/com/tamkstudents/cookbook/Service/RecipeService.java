package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.UserDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.FoodGroupDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.UserDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.RecipeRepository;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class RecipeService extends AbstractService{

    Logger logger = Logger.getLogger(RecipeService.class.getName());

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    UserRepository userRepository;

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
        List<RecipeDao> recipeDaos = recipeRepository.findAllByFoodGroup(dao);
        if(!recipeDaos.isEmpty()){
           recipeDaos.forEach(recipe -> recipes.add(new RecipeDto(recipe)));
        }
        long stopTime = System.nanoTime();
        logger.info("Recipes query: "+((stopTime-startTime)/1000000)+" ms");
        return recipes;
    }

    public RecipeDto createRecipe(RecipeDto dto) {
        long startTime = System.nanoTime();

        RecipeDto returnDto;
        Optional<UserDao> userDao = userRepository.findById(dto.getCreatorId());
        if (userDao.isPresent()) {
            RecipeDao recipeDao = new RecipeDao(dto, new UserDto(userDao.get()));
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
}
