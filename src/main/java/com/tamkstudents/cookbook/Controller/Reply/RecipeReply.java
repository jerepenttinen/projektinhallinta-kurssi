package com.tamkstudents.cookbook.Controller.Reply;

import com.tamkstudents.cookbook.Controller.Reply.Dto.IngredientWithQuantityReply;
import lombok.*;

import java.util.List;

@Builder
@Value
public class RecipeReply {
    long id;
    String recipeName;
    long creatorId;
    List<String> images;
    List<String> instructions;
    List<IngredientWithQuantityReply> ingredients;
    List<String> foodGroups;
}
