package com.task_tracker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.task_tracker.DTO.UserDTO;
import com.task_tracker.JWTConfig.JwtUtils;
import com.task_tracker.entities.User;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.repositories.UserRepo;
import com.task_tracker.requests.LoginRequest;
import com.task_tracker.requests.SignUpRequest;
import com.task_tracker.securityConfig.CustomUserDetails;

import jakarta.servlet.http.Cookie;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO signUp(SignUpRequest signUpRequest) throws UserException {
		// TODO Auto-generated method stub
		String username = signUpRequest.getUsername();
		 User existingUser = userRepo.findByUsername(username);
		 if(existingUser == null) {
			 User user = new User();
			 user.setFirstName(signUpRequest.getFirstName());
			 user.setLastName(signUpRequest.getLastName());
			 user.setUsername(username);
			 user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
			 user.setEmail(signUpRequest.getEmail());
			 userRepo.save(user);
			 return new UserDTO(user.getUserId(), user.getUsername());
		 }
		throw new UserException("User already present with the username "+username);
	}

	@Override
	public ResponseCookie logIn(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContext context = SecurityContextHolder.getContext();
		context.setAuthentication(authentication);
		
		CustomUserDetails userDetails = (CustomUserDetails)context.getAuthentication().getPrincipal();
		
		ResponseCookie cookie = jwtUtils.generateJwtCookie(userDetails);
		
		return cookie;
	}

}
