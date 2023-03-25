package com.task_tracker.services;

import java.util.List;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.Sprint;
import com.task_tracker.entities.Task;
import com.task_tracker.exceptions.ProjectNotFoundException;

public interface ProjectService {

	public Project createProject(String title);
	
	public List<Project> getAllProjectsOfAUser() ;
	
	public Project deleteProject(Long projectId) throws ProjectNotFoundException;
	
	public List<Task> getAllTasks(Long projectId) throws ProjectNotFoundException;
	
	public List<Sprint> getAllSprints(Long projectId) throws ProjectNotFoundException;
	
}
