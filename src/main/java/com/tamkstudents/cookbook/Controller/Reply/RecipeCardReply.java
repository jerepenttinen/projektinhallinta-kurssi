package com.tamkstudents.cookbook.Controller.Reply;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RecipeCardReply {
    long id;
    String recipeName;
    String image;
}
