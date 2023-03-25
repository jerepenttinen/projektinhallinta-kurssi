package com.tamkstudents.cookbook.Controller.Request.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class IngredientWithQuantityRequest {
    @NotBlank
    private final String ingredient;
    @NotBlank
    private final String quantity;
}
