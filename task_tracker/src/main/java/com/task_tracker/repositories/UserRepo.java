package com.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_tracker.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);

	
	
}
