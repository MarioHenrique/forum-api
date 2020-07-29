package com.forumapi.config.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionsHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public RequestValidationError handleValidationExceptions(MethodArgumentNotValidException exception) {

        List<FieldValidationError> fieldValidationError = collectEValidationsErrors(exception
                .getBindingResult()
                .getAllErrors());

        return new RequestValidationError(fieldValidationError);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public RequestValidationError handleValidationBindException(BindException exception){

        List<FieldValidationError> fieldValidationError = collectEValidationsErrors(exception.getAllErrors());

        return new RequestValidationError(fieldValidationError);
    }

    private List<FieldValidationError> collectEValidationsErrors(List<ObjectError> errors){
        return errors.stream()
                .map(FieldError.class::cast)
                .map(this::createFieldError)
                .collect(Collectors.toList());
    }

    private FieldValidationError createFieldError(FieldError error){
        return new FieldValidationError(error.getField(), error.getDefaultMessage());
    }

}
