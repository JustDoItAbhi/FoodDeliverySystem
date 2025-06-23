package com.restaurantservice.exceptions;

public class MenuNullException extends RuntimeException{
    public MenuNullException() {
    }

    public MenuNullException(String message) {
        super(message);
    }

    public MenuNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public MenuNullException(Throwable cause) {
        super(cause);
    }

    public MenuNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
