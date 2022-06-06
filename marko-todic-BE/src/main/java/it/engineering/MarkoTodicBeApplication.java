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
import java.util.Optional;

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
//		Optional<Role> role = roleRepository.findById(2);
//
//		User user = new User();
//		user.setEmail("jody@gmail.com");
//		user.setPassword(bCryptPasswordEncoder.encode("jody"));
//		user.setRoles(Collections.singleton(role.get()));
//
//		userRepository.save(user);
	}
}
