package com.task_tracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.Sprint;
import com.task_tracker.entities.Task;
import com.task_tracker.entities.User;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.repositories.ProjectRepo;
import com.task_tracker.repositories.UserRepo;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private GetPrinciple getPrinciple;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public Project createProject(String title) {
		// TODO Auto-generated method stub
		User user = getPrinciple.getLoggedInUser();
		Project project = new Project();
		project.setUser(user);
		project.setTitle(title);
		return projectRepo.save(project);
	}

	@Override
	public List<Project> getAllProjectsOfAUser() {
		// TODO Auto-generated method stub
		User user = getPrinciple.getLoggedInUser();
		return user.getProjects();
	}

	@Override
	public Project deleteProject(Long projectId) throws ProjectNotFoundException {
		// TODO Auto-generated method stub
		Optional<Project> projectO = projectRepo.findById(projectId);
		if(projectO.isPresent()) {
			Project project = projectO.get();
			projectRepo.delete(project);
			return project;
		}
       throw new ProjectNotFoundException("No project found with the id "+projectId);
	}

	@Override
	public List<Task> getAllTasks(Long projectId) throws ProjectNotFoundException {
		// TODO Auto-generated method stub
		Optional<Project> projectO = projectRepo.findById(projectId);
		if(projectO.isPresent())
			return projectO.get().getTasks();
		throw new ProjectNotFoundException("No project found with the id "+projectId);
	}

	@Override
	public List<Sprint> getAllSprints(Long projectId) throws ProjectNotFoundException {
		Optional<Project> projectO = projectRepo.findById(projectId);
		if(projectO.isPresent())
			return projectO.get().getSprints();
		throw new ProjectNotFoundException("No project found with the id "+projectId);
	}
	


}
