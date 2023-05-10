/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springbootrest.exceptions;

import java.io.FileNotFoundException;
import java.util.List;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public String fileNotFound(FileNotFoundException e) {
        return e.getMessage();
    }
    
    /*@ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto userNotFoundExceptionHandler(UserNotFoundException e){
        ExceptionDto dto = new ExceptionDto(e.getMessage());
        return dto;
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationExceptionDto validationFailedHandler(MethodArgumentNotValid e){
        
        ValidationExceptionDto dto = new ValidationExceptionDto();
        
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        
        for(FieldError fieldError: errors){
            dto.addMessage(fieldError.getDefaultMessage());
        }
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionResponse> constraintValidationExceptionHandler(ConstraintViolationException ex) {

        List<ExceptionResponse> exceptionResponses = new LinkedList<>();
        for (ConstraintViolation violation : ex.getConstraintViolations()) {
            exceptionResponses.add(new ExceptionResponse(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return exceptionResponses;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public List<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

        List<ExceptionResponse> exceptionResponses = new LinkedList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            exceptionResponses.add(new ExceptionResponse(fieldError.getField(), fieldError.getDefaultMessage()));
        }
        return exceptionResponses;
    }*/
}
