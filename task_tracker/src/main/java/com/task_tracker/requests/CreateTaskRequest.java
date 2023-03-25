package com.task_tracker.requests;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import lombok.Data;


@Data
public class CreateTaskRequest {

	private Long assignee;
	private String title;
	private String description;
	private String priority;
	private String typeName;
	private String status;
	private String startDate;
	private String dueDate;
	
}
