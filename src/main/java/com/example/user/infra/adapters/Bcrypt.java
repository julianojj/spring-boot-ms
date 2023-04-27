package com.example.user.infra.adapters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt implements Hash {
    @Override
    public String encrypt(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
