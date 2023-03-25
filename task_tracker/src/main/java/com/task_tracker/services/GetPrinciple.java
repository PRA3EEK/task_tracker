package com.task_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.task_tracker.entities.User;
import com.task_tracker.repositories.UserRepo;
import com.task_tracker.securityConfig.CustomUserDetails;

@Component
public class GetPrinciple {
	
	@Autowired
	private UserRepo userRepo;

	
	
	public  String getLoggedInUserUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	public  Long getLoggedInUserId() {
		String username =  SecurityContextHolder.getContext().getAuthentication().getName();
	    Long id = userRepo.findIdByUsername(username);
	    return id;
	}
	
	public User getLoggedInUser() {
		Long id =  getLoggedInUserId();
		return userRepo.findById(id).get();
	}
	
}
