package com.example.user.core.domain;

import com.example.user.core.exceptions.ValidationException;

import java.util.regex.Pattern;

public class User {
    public  String id;
    public  String name;
    public  String email;
    public String password;

    private User(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User create(String id, String name, String email, String password) throws Exception {
        User user = new User(id, name, email, password);
        user.validate();
        return user;
    }

    public static User createExistingUser(String id, String name, String email, String password) {
        return new User(id, name, email, password);
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
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–{}:;',?/*~$^+=<>]).{8,20}$");
        return !passwordPattern.matcher(this.password).matches();
    }
}
