package com.CustomerService.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionDto {
private String message;
private int code;

    public ExceptionDto(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
