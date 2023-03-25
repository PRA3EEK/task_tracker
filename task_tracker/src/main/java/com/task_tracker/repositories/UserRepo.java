package com.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.task_tracker.entities.User;

public interface UserRepo extends JpaRepository<User, Long>{

	User findByUsername(String username);
	
	@Query("select userId from User where username = ?1")
	Long findIdByUsername(String username);

	
	
}
