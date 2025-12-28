package com.auth.JWT.utility;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth.JWT.entity.User;
import com.auth.JWT.repository.UserRepository;


@Component
public class DbInitializer implements CommandLineRunner {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if(userRepository.findByUsername("Admin").isEmpty())
		{
			User user=new User();
			user.setUsername("Admin");
			user.setPassword(passwordEncoder.encode("admin123"));
			user.setRoles(List.of("Admin"));
			userRepository.save(user);
		}
		
	}

}
