package com.example.user.unit;


import com.example.user.core.domain.User;
import com.example.user.core.exceptions.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserTest {
    @Test
    public void returnExceptionIfEmptyId() {
        Exception err = assertThrows(ValidationException.class, () -> {
            User.create("", "Juliano", "juliano@test.com", "-Secr3t@");
        });
        assertEquals("Id cannot be empty", err.getMessage());
    }

    @Test
    public void returnExceptionIfEmptyName() {
        Exception err = assertThrows(ValidationException.class, () -> {
            User.create("1", "", "juliano@test.com", "-Secr3t@");
        });
        assertEquals("Name cannot be empty", err.getMessage());
    }

    @Test
    public void returnExceptionIfInvalidEmail() {
        Exception err = assertThrows(ValidationException.class, () -> {
            User.create("1", "Juliano", "juliano@", "-Secr3t@");
        });
        assertEquals("Invalid email", err.getMessage());
    }

    @Test
    public void returnExceptionIfInvalidPassword() {
        Exception err = assertThrows(ValidationException.class, () -> {
            User.create("1", "Juliano", "juliano@test.com", "123456");
        });
        assertEquals("Invalid password", err.getMessage());
    }
}
