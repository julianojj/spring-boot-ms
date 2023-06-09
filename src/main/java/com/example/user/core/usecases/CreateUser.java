package com.example.user.core.usecases;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;
import com.example.user.core.exceptions.UserAlreadyExistsException;
import com.example.user.infra.adapters.Hash;
import com.example.user.infra.adapters.Queue;
import com.google.gson.JsonObject;

import java.util.UUID;

public class CreateUser {
    private final UserRepository userRepository;
    private final Queue queue;
    private final Hash hash;

    public CreateUser(UserRepository userRepository, Queue queue, Hash hash) {
        this.userRepository = userRepository;
        this.queue = queue;
        this.hash = hash;
    }

    public CreateUserOutput execute(CreateUserInput input) throws Exception {
        String userId = UUID.randomUUID().toString();
        User user = User.create(userId, input.name(), input.email(), input.password());
        User existingUser = userRepository.findByEmail(user.email);
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        user.password = this.hash.encrypt(user.password);
        this.userRepository.save(user);
        JsonObject userObject = new JsonObject();
        userObject.addProperty("id", user.id);
        userObject.addProperty("name", user.name);
        userObject.addProperty("email", user.email);
        this.queue.publisher("CreatedUser", userObject.toString().getBytes());
        return new CreateUserOutput(user.id);
    }
}
