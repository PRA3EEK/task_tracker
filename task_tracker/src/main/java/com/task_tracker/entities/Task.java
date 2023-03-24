package com.task_tracker.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long assignee;
	private String title;
	private String description;
	@ElementCollection
	private Map<String, Long> comments = new HashMap<>();
	private String priority;
	private String typeName;
	private String status;
	private LocalDate startDate;
	private LocalDate dueDate;
}
