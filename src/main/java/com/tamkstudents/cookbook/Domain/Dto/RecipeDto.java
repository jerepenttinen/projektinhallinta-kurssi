package com.tamkstudents.cookbook.Domain.Dto;

import com.tamkstudents.cookbook.Domain.Dao.RecipeDao;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
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
    private String recipeName;
    private long creatorId;
    private byte[] image;
    private List<String> instruction;
    private List<IngredientDto> ingredients;
    private List<FoodGroupDto> foodGroups;
}
