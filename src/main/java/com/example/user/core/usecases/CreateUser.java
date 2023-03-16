package com.example.user.core.usecases;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;
import com.example.user.core.exceptions.UserAlreadyExistsException;
import com.example.user.infra.adapters.Queue;
import org.springframework.util.SerializationUtils;

import java.util.UUID;

public class CreateUser {
    private final UserRepository userRepository;
    private final Queue queue;

    public CreateUser(UserRepository userRepository, Queue queue) {
        this.userRepository = userRepository;
        this.queue = queue;
    }

    public CreateUserOutput execute(CreateUserInput input) throws Exception {
        String userId = UUID.randomUUID().toString();
        User user = new User(userId, input.name(), input.email(), input.password());
        User existingUser = userRepository.findByEmail(user.email);
        if (existingUser != null) {
            throw new UserAlreadyExistsException();
        }
        this.userRepository.save(user);
        this.queue.publisher("CreatedUser", user.id.getBytes());
        return new CreateUserOutput(user.id);
    }
}
