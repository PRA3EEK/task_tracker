package com.task_tracker.services;

import java.util.List;

import com.task_tracker.entities.Project;

public interface ProjectService {

	public Project createProject(String title);
	
	public List<Project> getAllProjectsOfAUser();
	
	public Project deleteProject(Long projectId);
	
}
