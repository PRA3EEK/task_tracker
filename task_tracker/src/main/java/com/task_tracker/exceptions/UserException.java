package com.task_tracker.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends Exception{

	private static final long serialVersionUID = 1L;
	private String msg;
}
