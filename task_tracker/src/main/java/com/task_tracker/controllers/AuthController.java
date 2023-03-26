package com.task_tracker.controllers;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.DTO.UserDTO;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.requests.LoginRequest;
import com.task_tracker.requests.SignUpRequest;
import com.task_tracker.services.AuthService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<UserDTO> signUp(@RequestBody SignUpRequest request) throws UserException {
		return new ResponseEntity<>(authService.signUp(request), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
		ResponseCookie cookie = authService.logIn(loginRequest);
		
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(cookie);
	}
	
	@DeleteMapping("/logout")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> userLogout() throws UserException
	{	
		ResponseCookie cookie = authService.logout();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(cookie);
	
	}
	
}
