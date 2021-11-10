package com.hms.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.model.User;


public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
	
	Page<User> findByUsernameContaining(String username, Pageable paging);
}
