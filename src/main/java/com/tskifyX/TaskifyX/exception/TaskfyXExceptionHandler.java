package com.tskifyX.TaskifyX.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class TaskfyXExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String, String>> handleUserException(UserException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("Root", "User Error");  // Default title
        response.put("Error Message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
