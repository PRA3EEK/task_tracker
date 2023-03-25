package com.task_tracker.entities;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
	@ElementCollection(fetch = FetchType.EAGER)
	private Map<String, Long> comments = new HashMap<>();
	private String priority;
	private String typeName;
	private String status;
	private LocalDate startDate;
	private LocalDate dueDate;
	
	@ManyToOne@JsonIgnore
	private Project project;
	
	@ManyToOne@JsonIgnore
	private Sprint sprint;
	
}
