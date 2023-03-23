package com.tamkstudents.cookbook.Controller.Reply.Dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class IngredientWithQuantityReply {
    long id;
    String ingredient;
    String quantity;
}
