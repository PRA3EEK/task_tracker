package com.task_tracker.services;

import org.springframework.http.ResponseCookie;

import com.task_tracker.DTO.UserDTO;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.requests.LoginRequest;
import com.task_tracker.requests.SignUpRequest;


public interface AuthService {

	public UserDTO signUp(SignUpRequest signUpRequest) throws UserException;
	
	public ResponseCookie logIn(LoginRequest loginRequest);
	
}
