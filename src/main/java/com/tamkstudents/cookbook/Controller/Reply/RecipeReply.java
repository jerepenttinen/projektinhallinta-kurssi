package com.tamkstudents.cookbook.Controller.Reply;

import com.tamkstudents.cookbook.Domain.Dto.IngredientDto;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class RecipeReply {
    private final long id;
    private final String recipeName;
    private final long creatorId;
    private final List<String> images;
    private final List<String> instructions;
    private final List<IngredientDto> ingredients;
    private final List<String> foodGroups;
}
