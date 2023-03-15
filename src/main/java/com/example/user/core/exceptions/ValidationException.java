package com.example.user.core.exceptions;

public class ValidationException extends Exception {
    public final int code = 422;

    public ValidationException(String message) {
        super(message);
    }
}
