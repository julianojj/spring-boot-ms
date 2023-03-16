package com.example.user.infra.repository;

import com.example.user.core.domain.User;
import com.example.user.core.domain.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepositoryDatabase implements UserRepository {
    private final Connection connection;

    public UserRepositoryDatabase(
            Connection connection
    ) throws SQLException {
        this.connection = connection;
    }

    @Override
    public void save(User user) throws SQLException {
        PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO Users(Id, Name, Email, Password) VALUES(?, ?, ?, ?)"
        );
        statement.setString(1, user.id);
        statement.setString(2, user.name);
        statement.setString(3, user.email);
        statement.setString(4, user.password);
        statement.execute();
    }

    @Override
    public User find(String id) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM Users WHERE Id = ?"
        );
        statement.setString(1, id);
        ResultSet userData = statement.executeQuery();
        User user = null;
        while (userData.next()) {
            user = new User(
                    userData.getString("Id"),
                    userData.getString("Name"),
                    userData.getString("Email"),
                    userData.getString("Password")
            );
        }
        return user;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM Users WHERE Email = ?"
        );
        statement.setString(1, email);
        ResultSet userData = statement.executeQuery();
        User user = null;
        while (userData.next()) {
            user = new User(
                    userData.getString("Id"),
                    userData.getString("Name"),
                    userData.getString("Email"),
                    userData.getString("Password")
            );
        }
        return user;
    }
}
