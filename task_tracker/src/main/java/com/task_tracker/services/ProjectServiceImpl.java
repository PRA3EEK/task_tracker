package com.task_tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.User;
import com.task_tracker.repositories.ProjectRepo;
import com.task_tracker.repositories.UserRepo;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Project createProject(String title) {
		// TODO Auto-generated method stub
		Project project = new Project();
		project.setTitle(title);
		return projectRepo.save(project);
	}

	@Override
	public List<Project> getAllProjectsOfAUser() {
		// TODO Auto-generated method stub
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user  = userRepo.findByUsername(username);
		return user.getProjects();
	}

	@Override
	public Project deleteProject(Long projectId) {
		// TODO Auto-generated method stub
		Optional<Project> projectO = projectRepo.findById(projectId);
		if(projectO.isPresent()) {
			Project project = projectO.get();
			projectRepo.delete(project);
			return project;
		}
		return null;
	}
	


}
