package com.CustomerService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
@ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ExceptionDto> customerNotFount(CustomerNotFoundException e){
    ExceptionDto dto=new ExceptionDto(
            e.getMessage(),
         404   );
    return ResponseEntity.ok(dto);
}

    @ExceptionHandler(CustomerAlreadyExsits.class)
    public ResponseEntity<ExceptionDto> customerAlreadyExists(CustomerAlreadyExsits e){
        ExceptionDto dto=new ExceptionDto(
                e.getMessage(),
                404   );
        return ResponseEntity.ok(dto);
    }
}
