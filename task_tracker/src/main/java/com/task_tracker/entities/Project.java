package com.task_tracker.entities;



import java.util.ArrayList;
import java.util.List;



import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
@Data@AllArgsConstructor@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
 	private Long projectId;
	private String title;
	@ManyToOne@JsonIgnore
	private User user;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "project")
	@JsonIgnore
	private List<Task> tasks = new ArrayList<>();
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Sprint> sprints = new ArrayList<>();

	
}
