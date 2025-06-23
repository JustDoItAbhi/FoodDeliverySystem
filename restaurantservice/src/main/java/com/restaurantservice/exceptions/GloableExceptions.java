package com.restaurantservice.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GloableExceptions {
    @ExceptionHandler(RestaurantAlreadyExists.class)
    public ResponseEntity<MessageDto> alreadyExists(RestaurantAlreadyExists e){
        MessageDto dto=new MessageDto(
                e.getMessage(),
                404
        );
        return ResponseEntity.ok(dto);
    }
    @ExceptionHandler(RestaurantNotExists.class)
    public ResponseEntity<MessageDto> NotExists(RestaurantNotExists e){
        MessageDto dto=new MessageDto(
                e.getMessage(),
                404
        );
        return ResponseEntity.ok(dto);
    }
    @ExceptionHandler(MenuNullException.class)
    public ResponseEntity<MessageDto> NotMenu(MenuNullException e){
        MessageDto dto=new MessageDto(
                e.getMessage(),
                404
        );
        return ResponseEntity.ok(dto);
    }
    @ExceptionHandler(NonVegMenuAlreadyExists.class)
    public ResponseEntity<MessageDto> menuAlreadyexists(NonVegMenuAlreadyExists e){
        MessageDto dto=new MessageDto(
                e.getMessage(),
                404
        );
        return ResponseEntity.ok(dto);
    }
}
