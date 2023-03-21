package com.tamkstudents.cookbook.Controller.Request;

import com.tamkstudents.cookbook.Controller.Request.Dto.IngredientWithQuantity;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CreateRecipeRequest {
    @NotBlank
    private final String recipeName;

    private final List<String> images;

    private final List<String> instructions;

    private final List<IngredientWithQuantity> ingredients;

    private final List<String> categories;
}
