package com.example.paymentservice.config.exception.user;

import com.example.paymentservice.core.dtos.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExeptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseExceptionDTO> userNotFound(UserNotFoundException exception){
        ResponseExceptionDTO responseException = new ResponseExceptionDTO(exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(responseException);
    }
}
