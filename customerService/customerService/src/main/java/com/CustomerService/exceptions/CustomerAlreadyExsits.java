package com.CustomerService.exceptions;

public class CustomerAlreadyExsits extends RuntimeException{
    public CustomerAlreadyExsits() {
    }

    public CustomerAlreadyExsits(String message) {
        super(message);
    }

    public CustomerAlreadyExsits(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerAlreadyExsits(Throwable cause) {
        super(cause);
    }

    public CustomerAlreadyExsits(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
