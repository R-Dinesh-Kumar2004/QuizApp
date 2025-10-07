package com.project.QuizApp.controller;


import com.project.QuizApp.model.ExceptionHandlerLog;
import com.project.QuizApp.util.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler  {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex){
        return  new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> handleInternalServerErrorException(InternalServerErrorException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionHandlerLog(
                new Date(), ex.getMessage(), request.getDescription(false)),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<?> handleUserAlreadyExistException(UserAlreadyExistException ex ,
                                                             WebRequest request) {

        return new ResponseEntity<>(new ExceptionHandlerLog(new Date(),
                ex.getMessage(),request.getDescription(false)),
                HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException ex,WebRequest request) {
        return new ResponseEntity<>(new ExceptionHandlerLog(new Date(),
                ex.getMessage(),request.getDescription(false)),
                HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(OtpExpiredException.class)
    public ResponseEntity<?> handleOtpExpiredException(OtpExpiredException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionHandlerLog(new Date(),
                ex.getMessage(),request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<?> handleInvalidOtpException(InvalidOtpException ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionHandlerLog(new Date(),
                ex.getMessage(),request.getDescription(false)),
                HttpStatus.BAD_REQUEST);
    }
}
