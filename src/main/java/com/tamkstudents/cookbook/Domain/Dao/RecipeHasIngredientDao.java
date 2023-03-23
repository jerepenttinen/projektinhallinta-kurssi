package com.tamkstudents.cookbook.Domain.Dao;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "recipe_has_ingredients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class RecipeHasIngredientDao {
    @EmbeddedId
    private RecipeHasIngredientId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    private RecipeDao recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private IngredientDao ingredient;

    @Column(name = "quantity")
    private String quantity;

    public RecipeHasIngredientDao(RecipeDao recipe, IngredientDao ingredient, String quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.id = new RecipeHasIngredientId(recipe.getId(), ingredient.getId());
    }
}
