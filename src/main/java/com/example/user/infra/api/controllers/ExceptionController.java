package com.example.user.infra.api.controllers;

import com.example.user.core.exceptions.NotFoundException;
import com.example.user.core.exceptions.UserAlreadyExistsException;
import com.example.user.core.exceptions.ValidationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<String> validationException(ValidationException err) {
        return new ResponseEntity<String>(err.getMessage(), HttpStatusCode.valueOf(err.code));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExistsException(UserAlreadyExistsException err) {
        return new ResponseEntity<String>(err.getMessage(), HttpStatusCode.valueOf(err.code));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException err) {
        return new ResponseEntity<String>(err.getMessage(), HttpStatusCode.valueOf(err.code));
    }
}
