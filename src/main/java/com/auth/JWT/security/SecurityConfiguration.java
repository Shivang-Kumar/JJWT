package com.auth.JWT.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth.JWT.service.CustomUserDeatilService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	

	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,JwtFilter jwtFilter) throws Exception
	{
		 http.authorizeHttpRequests(auth -> auth.requestMatchers("/api/login").permitAll()
				 .anyRequest().authenticated());
		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		 
		 http.csrf(csrf -> csrf.disable());
				
		
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
