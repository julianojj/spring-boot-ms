package com.example.user.core.domain;

import com.example.user.core.exceptions.ValidationException;

import java.util.regex.Pattern;

public class User {
    public final String id;
    public final String name;
    public final String email;
    public final String password;

    public User(String id, String name, String email, String password) throws Exception {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.validate();
    }

    private void validate() throws Exception {
        if (this.id.isEmpty()) throw new ValidationException("Id cannot be empty");
        if (this.name.isEmpty()) throw new ValidationException("Name cannot be empty");
        if (this.isInvalidEmail()) throw new ValidationException("Invalid email");
        if (this.isInvalidPassword()) throw new ValidationException("Invalid password");
    }

    private boolean isInvalidEmail() {
        Pattern emailPattern = Pattern.compile("^(.+)@(.+)$");
        return !emailPattern.matcher(this.email).matches();
    }

    private boolean isInvalidPassword() {
        Pattern passwordPattern = Pattern.compile(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$");
        return !passwordPattern.matcher(this.password).matches();
    }
}
