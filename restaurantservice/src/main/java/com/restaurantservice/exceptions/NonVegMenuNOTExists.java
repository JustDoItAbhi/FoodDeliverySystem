package com.restaurantservice.exceptions;

public class NonVegMenuNOTExists extends RuntimeException{
    public NonVegMenuNOTExists() {
    }

    public NonVegMenuNOTExists(String message) {
        super(message);
    }

    public NonVegMenuNOTExists(String message, Throwable cause) {
        super(message, cause);
    }

    public NonVegMenuNOTExists(Throwable cause) {
        super(cause);
    }

    public NonVegMenuNOTExists(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
