package br.com.unipar.BullkApp.controllers;

import br.com.unipar.BullkApp.exceptions.ApiErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorMessage handleException(Exception ex){
        return new ApiErrorMessage(ex.getMessage());
    }
}
