package com.restaurantservice.entity;

public enum TypeOfSpicy {
    HOT,MEDIUM_SPICY,LESS_SPICY;

    public static TypeOfSpicy fromString(String inputString){
        return TypeOfSpicy.valueOf(inputString.toUpperCase());
    }
}
