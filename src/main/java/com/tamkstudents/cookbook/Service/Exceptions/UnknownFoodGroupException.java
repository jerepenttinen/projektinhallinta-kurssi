package com.tamkstudents.cookbook.Service.Exceptions;

import lombok.Getter;

@Getter
public class UnknownFoodGroupException extends Exception {
    String foodGroup;
    public UnknownFoodGroupException(String foodGroup) {
        super(foodGroup);
        this.foodGroup = foodGroup;
    }
}
