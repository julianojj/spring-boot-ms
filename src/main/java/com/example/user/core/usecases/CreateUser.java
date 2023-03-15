package com.example.user.core.usecases;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;
import com.example.user.core.exceptions.UserAlreadyExistsException;

import java.util.UUID;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CreateUserOutput execute(CreateUserInput input) throws Exception {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, input.name(), input.email(), input.password());
        User existingUser = userRepository.findByEmail(user.email);
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        this.userRepository.save(user);
        return new CreateUserOutput(user.id);
    }
}
