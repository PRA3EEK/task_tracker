package com.task_tracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaskNotFoundException extends Exception {

	private String message;
}
