package com.restaurantservice.exceptions;

public class NonVegMenuAlreadyExists  extends RuntimeException{
    public NonVegMenuAlreadyExists() {
    }

    public NonVegMenuAlreadyExists(String message) {
        super(message);
    }

    public NonVegMenuAlreadyExists(String message, Throwable cause) {
        super(message, cause);
    }

    public NonVegMenuAlreadyExists(Throwable cause) {
        super(cause);
    }

    public NonVegMenuAlreadyExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
