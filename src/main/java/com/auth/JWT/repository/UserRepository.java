package com.auth.JWT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.auth.JWT.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<UserDetails> findByUsername(String username);

}
