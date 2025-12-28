package com.auth.JWT.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import com.auth.JWT.service.CustomUserDeatilService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		 http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/non-secured").permitAll()
				 .anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults());
				
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	@Bean
	public UserDetailsService userDeatilService()
	{
		return new CustomUserDeatilService();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(UserDetailsService userDetailService,PasswordEncoder passwordEncoder)
	{
		DaoAuthenticationProvider dao=new DaoAuthenticationProvider(userDetailService);
		dao.setPasswordEncoder(passwordEncoder);
		return new ProviderManager(dao);
	}
	
	

}
