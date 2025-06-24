package com.restaurantservice.entity;

public enum PizzaSize {
    SMALL,MEDIUM, LARGE;
    public static PizzaSize fromString(String input){
        return PizzaSize.valueOf(input.toUpperCase());
    }


}
