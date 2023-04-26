package com.tamkstudents.cookbook.Controller.Mapper;

import com.tamkstudents.cookbook.Controller.Reply.Dto.IngredientWithQuantityReply;
import com.tamkstudents.cookbook.Domain.Dao.RecipeHasIngredientDao;
import org.springframework.stereotype.Service;

@Service
public class IngredientMapperService {
    public IngredientWithQuantityReply ingredientWithQuantityReplyFromRecipeHasIngredientsDao(RecipeHasIngredientDao ingredient) {
        return IngredientWithQuantityReply
                .builder()
                .id(ingredient.getIngredient().getId())
                .quantity(ingredient.getQuantity())
                .ingredient(ingredient.getIngredient().getName())
                .build();
    }
}
