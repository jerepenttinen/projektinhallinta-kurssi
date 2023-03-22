package com.tamkstudents.cookbook.Controller.Request;

import com.tamkstudents.cookbook.Controller.Request.Dto.IngredientWithQuantity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.List;

@Value
public class CreateRecipeRequest {
    @NotBlank
    String recipeName;

    @NotNull
    List<String> images;

    @NotNull
    List<String> instructions;

    @NotNull
    List<IngredientWithQuantity> ingredients;

    @NotNull
    List<String> categories;
}
