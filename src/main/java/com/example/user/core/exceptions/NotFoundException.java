package com.example.user.core.exceptions;

public class NotFoundException extends Exception {
    public final int code = 404;

    public NotFoundException() {
        super("User not found");
    }
}
