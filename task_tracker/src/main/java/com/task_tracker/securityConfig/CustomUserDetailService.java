package com.task_tracker.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.User;
import com.task_tracker.repositories.UserRepo;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepo userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUsername(username);
		if(user != null) {
			return CustomUserDetails.build(user);
		}
       throw new UsernameNotFoundException("Username "+username+" not found!");
	}

}
