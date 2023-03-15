package com.example.user.core.exceptions;

public class UserAlreadyExistsException extends Exception {
    public final int code = 409;

    public UserAlreadyExistsException() {
        super("User already exists");
    }
}
