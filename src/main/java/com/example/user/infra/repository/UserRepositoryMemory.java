package com.example.user.infra.repository;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;

import java.util.ArrayList;
import java.util.Objects;

public class UserRepositoryMemory implements UserRepository {
    public final ArrayList<User> users = new ArrayList<>();

    @Override
    public void save(User user) {
        this.users.add(user);
    }

    @Override
    public User find(String id) {
        for(User user: this.users) {
            if(Objects.equals(user.id, id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        for(User user: this.users) {
            if(Objects.equals(user.email, email)) {
                return user;
            }
        }
        return null;
    }
}
