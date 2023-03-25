package com.task_tracker.exceptions;

import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException manve, WebRequest wr){		
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), "Validation Error", manve.getBindingResult().getFieldError().getDefaultMessage());	
	   return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProjectNotFoundException.class)
	public ResponseEntity<ErrorDetails> projectNotFoundExceptionHandler(ProjectNotFoundException pnfe, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), pnfe.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(SprintNotfoundException.class)
	public ResponseEntity<ErrorDetails> sprintNotfoundExceptionHandler(SprintNotfoundException snfe, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), snfe.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TaskNotFoundException.class)
	public ResponseEntity<ErrorDetails> taskNotFoundException(TaskNotFoundException tnfe, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), tnfe.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserException.class)
	public ResponseEntity<ErrorDetails> userExceptionHandler(UserException ue, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), ue.getMessage(), wr.getDescription(false));
		return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorDetails> sqlExceptionHandler(SQLException sqlException, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), sqlException.getMessage(), wr.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorDetails> validationExceptionHandler(ValidationException validationException, WebRequest wr){
		ErrorDetails ed = new ErrorDetails(LocalDateTime.now(), validationException.getMessage(), wr.getDescription(false));
	   return new ResponseEntity<ErrorDetails>(ed, HttpStatus.BAD_REQUEST);
	}
	
}
