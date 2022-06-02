package it.engineering;

import it.engineering.entity.Role;
import it.engineering.entity.User;
import it.engineering.repository.RoleRepository;
import it.engineering.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class MarkoTodicBeApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(MarkoTodicBeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Role role = new Role();
//		role.setName("ROLE_ADMIN");
//		roleRepository.save(role);
//
//		User user = new User();
//		user.setEmail("radnik1@mail.com");
//		user.setPassword(bCryptPasswordEncoder.encode("radnik1"));
//		user.setRoles(Collections.singleton(role));
//
//		userRepository.save(user);
	}
}
