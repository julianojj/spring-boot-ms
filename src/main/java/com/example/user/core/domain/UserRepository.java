package com.example.user.core.domain;

import java.sql.SQLException;

public interface UserRepository {
    void save(User user) throws SQLException;
    User find(String id) throws Exception;
    User findByEmail(String email) throws Exception;
}
