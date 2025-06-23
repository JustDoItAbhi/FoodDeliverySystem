package com.restaurantservice.exceptions;

public class RestaurantNotExists extends RuntimeException{
    public RestaurantNotExists() {
    }

    public RestaurantNotExists(String message) {
        super(message);
    }

    public RestaurantNotExists(String message, Throwable cause) {
        super(message, cause);
    }

    public RestaurantNotExists(Throwable cause) {
        super(cause);
    }

    public RestaurantNotExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
