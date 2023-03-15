package com.example.user.integration;

import com.example.user.core.domain.User;
import com.example.user.core.usecases.CreateUser;
import com.example.user.core.usecases.CreateUserInput;
import com.example.user.core.usecases.CreateUserOutput;
import com.example.user.infra.repository.UserRepositoryDatabase;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class CreateUserTest {
    Connection connection;
    UserRepositoryDatabase userRepository;

    public CreateUserTest() throws SQLException {
        this.connection = DriverManager.getConnection(
                "jdbc:mariadb://localhost:3306/msuser",
                "juliano",
                "12345678"
        );
        this.userRepository = new UserRepositoryDatabase(connection);
    }

    @Test
    public void testShouldCreateUser() throws Exception {
        this.connection.createStatement().executeQuery("DELETE FROM Users");
        CreateUser createUser = new CreateUser(this.userRepository);
        CreateUserInput input = new CreateUserInput("Juliano", "juliano@test.com", "-Secr3t@");
        CreateUserOutput output = createUser.execute(input);
        User user = userRepository.find(output.id());
        assertEquals(input.name(), user.name);
    }
}
