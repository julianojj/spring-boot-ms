package com.example.user;

import com.example.user.core.domain.UserRepository;
import com.example.user.core.usecases.CreateUser;
import com.example.user.core.usecases.GetUser;
import com.example.user.infra.repository.UserRepositoryDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@RestController
public class UserApplication {
	private final UserRepository userRepository;

	public UserApplication() throws Exception {
		Dotenv dotenv = Dotenv.load();
		Connection connection = DriverManager.getConnection(
				dotenv.get("mariadb_connection"),
				dotenv.get("mariadb_user"),
				dotenv.get("mariadb_password")
		);
		this.userRepository = new UserRepositoryDatabase(connection);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public CreateUser createUser() {
		return new CreateUser(userRepository);
	}

	@Bean
    public GetUser getUser() {
        return new GetUser(userRepository);
    }
}
