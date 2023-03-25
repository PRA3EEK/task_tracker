package com.task_tracker.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.cglib.core.Local;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor@NoArgsConstructor
public class Sprint {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long sprintId;
	private Integer weeks;
	private LocalDate startDate;
	private LocalDate endDate;
	@OneToMany(mappedBy = "sprint")
	List<Task> sprintTasks = new ArrayList<>();
	@ManyToOne
	private Project project;
}
