package com.example.user;

import com.example.user.core.domain.UserRepository;
import com.example.user.core.usecases.CreateUser;
import com.example.user.core.usecases.GetUser;
import com.example.user.infra.adapters.Bcrypt;
import com.example.user.infra.adapters.Hash;
import com.example.user.infra.adapters.Queue;
import com.example.user.infra.adapters.RabbitMQ;
import com.example.user.infra.repository.UserRepositoryDatabase;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;

@SpringBootApplication
@RestController
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
public class UserApplication {
	private final UserRepository userRepository;
	private final Queue queue;
	private final Hash hash;

	public UserApplication() throws Exception {
		Dotenv dotenv = Dotenv.load();
		Connection connection = DriverManager.getConnection(
				dotenv.get("mariadb_connection"),
				dotenv.get("mariadb_user"),
				dotenv.get("mariadb_password")
		);
		this.userRepository = new UserRepositoryDatabase(connection);
		this.queue = new RabbitMQ();
		this.hash = new Bcrypt();
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder BCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CreateUser createUser() {
		return new CreateUser(userRepository, queue, hash);
	}

	@Bean
    public GetUser getUser() {
        return new GetUser(userRepository);
    }
}
