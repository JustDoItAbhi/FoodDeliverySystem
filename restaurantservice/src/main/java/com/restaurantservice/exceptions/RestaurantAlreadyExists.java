package com.restaurantservice.exceptions;

public class RestaurantAlreadyExists extends RuntimeException{
    public RestaurantAlreadyExists() {
    }

    public RestaurantAlreadyExists(String message) {
        super(message);
    }

    public RestaurantAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantAlreadyExists(Throwable cause) {
        super(cause);
    }

    public RestaurantAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
