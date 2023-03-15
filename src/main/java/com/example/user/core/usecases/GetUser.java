package com.example.user.core.usecases;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;
import com.example.user.core.exceptions.NotFoundException;

public class GetUser {
    private final UserRepository userRepository;

    public GetUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public GetUserOutput execute(String userId) throws Exception {
        User user = this.userRepository.find(userId);
        if (user == null) {
            throw new NotFoundException();
        }
        return new GetUserOutput(
                user.id,
                user.name,
                user.email
        );
    }
}
