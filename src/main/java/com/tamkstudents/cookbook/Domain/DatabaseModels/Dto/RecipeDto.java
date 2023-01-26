package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.FoodGroupDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.IngredientDao;
import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class RecipeDto implements DtoEntity {
    //constructor From Data acces object
    public RecipeDto(RecipeDao dao){
        this.id = dao.getId();
        this.recipeName = dao.getRecipeName();
        this.creatorId = dao.getCreator().getUser_id();
        this.foodGroups = new ArrayList<>();
        this.ingredients = new ArrayList<>();

        dao.getFoodGroups().forEach(foodGroupDao -> this.foodGroups.add(new FoodGroupDto(foodGroupDao)));
        dao.getIngredients().forEach(ingredientDao -> this.ingredients.add(new IngredientDto(ingredientDao)));
    }

    private int id;
    private String recipeName;
    private int creatorId;
    private List<IngredientDto> ingredients;
    private List<FoodGroupDto> foodGroups;
}
