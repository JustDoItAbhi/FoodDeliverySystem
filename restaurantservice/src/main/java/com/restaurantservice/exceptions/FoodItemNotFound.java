package com.restaurantservice.exceptions;

public class FoodItemNotFound extends RuntimeException{
    public FoodItemNotFound() {
    }

    public FoodItemNotFound(String message) {
        super(message);
    }

    public FoodItemNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public FoodItemNotFound(Throwable cause) {
        super(cause);
    }

    public FoodItemNotFound(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
