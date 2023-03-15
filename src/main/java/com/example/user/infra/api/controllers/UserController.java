package com.example.user.infra.api.controllers;

import com.example.user.core.usecases.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private CreateUser createUser;
    @Autowired
    private GetUser getUser;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody CreateUserInput input) throws Exception {
        CreateUserOutput output = this.createUser.execute(input);
        return new ResponseEntity<>(
                output.id(),
                HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<GetUserOutput> getUser(@PathVariable String id) throws Exception {
        GetUserOutput output = this.getUser.execute(id);
        return new ResponseEntity<>(
                output,
                HttpStatus.ACCEPTED
        );
    }
}
