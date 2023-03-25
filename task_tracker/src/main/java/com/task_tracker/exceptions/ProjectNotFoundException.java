package com.task_tracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProjectNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	private String message;
}
