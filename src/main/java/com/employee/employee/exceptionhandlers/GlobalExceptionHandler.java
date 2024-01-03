package com.employee.employee.exceptionhandlers;

import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    //Handles Exception throw out project
    @ExceptionHandler(InspireNetException.class)
    @ResponseBody
    public ResponseEntity<String> handleIllegalArgumentException(InspireNetException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }
}
