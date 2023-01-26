package com.tamkstudents.cookbook.Service;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dto.RecipeDto;
import com.tamkstudents.cookbook.Domain.DatabaseModels.RepositoryInterface.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class RecipeService extends AbstractService{

    Logger logger = Logger.getLogger(RecipeService.class.getName());

    @Autowired
    RecipeRepository recipeRepository;

    public List<RecipeDto> getAllRecipes(){
        long startTime = System.nanoTime();
        List<RecipeDto> dtoList = new ArrayList<>();
        recipeRepository.findAll().forEach(recipeDao ->  dtoList.add(new RecipeDto(recipeDao)));
        long stopTime = System.nanoTime();
        logger.info("Recipes query: "+((stopTime-startTime)/1000000)+" ms");
        return dtoList;
    }

    public RecipeDto getRecipeById(int id){
        Optional<RecipeDao> dao = recipeRepository.findById(id);

        if(dao.isPresent()) {
            return new RecipeDto(dao.get());
        }else {
            logger.severe("Recipe not found - id:" + dao.get().getId());
            return null;
        }
    }
}
