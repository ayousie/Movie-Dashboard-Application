package fawry.task.admin.dashboard;

import fawry.task.admin.dashboard.model.Role;
import fawry.task.admin.dashboard.model.User;
import fawry.task.admin.dashboard.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner seedUsers(UserRepository users, PasswordEncoder encoder) {
		return args -> {
			if (!users.existsByUsername("admin")) {
				users.save(User.builder().username("admin").password(encoder.encode("admin123")).role(Role.ADMIN).build());
			}
			if (!users.existsByUsername("user")) {
				users.save(User.builder().username("user").password(encoder.encode("user123")).role(Role.USER).build());
			}
		};
	}
}
