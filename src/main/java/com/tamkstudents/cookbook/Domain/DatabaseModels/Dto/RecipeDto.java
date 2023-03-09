package com.tamkstudents.cookbook.Domain.DatabaseModels.Dto;

import com.tamkstudents.cookbook.Domain.DatabaseModels.Dao.RecipeDao;
import com.tamkstudents.cookbook.Domain.DtoEntity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RecipeDto implements DtoEntity {
    //constructor From Data access object
    public RecipeDto(RecipeDao dao){
        this.id = dao.getId();
        this.recipeName = dao.getRecipeName();
        this.creatorId = dao.getCreator().getId();
        this.image = dao.getImage();
        this.instruction = dao.getInstruction();
        this.foodGroups = dao.getFoodGroups().stream().map(FoodGroupDto::new).toList();
        this.ingredients = dao.getIngredients().stream().map(IngredientDto::new).toList();
    }

    private long id;
    @NotNull
    private final String recipeName;
    @NotNull
    private final long creatorId;
    @NotNull
    private final byte[] image;
    @NotNull
    private final List<String> instruction;
    @NotNull
    private final List<IngredientDto> ingredients;
    @NotNull
    private final List<FoodGroupDto> foodGroups;
}
