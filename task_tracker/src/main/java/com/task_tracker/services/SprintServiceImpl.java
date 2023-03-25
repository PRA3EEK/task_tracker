package com.task_tracker.services;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.Sprint;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.repositories.ProjectRepo;
import com.task_tracker.repositories.SprintRepo;
import com.task_tracker.requests.CreateSprintRequest;

@Service
public class SprintServiceImpl implements SprintService{

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private SprintRepo sprintRepo;

	@Override
	public Sprint createSprint(Long projectId, CreateSprintRequest request) throws ProjectNotFoundException {
		Project project = null;
		Optional<Project> projectO = projectRepo.findById(projectId);
		if(!projectO.isPresent()) throw new ProjectNotFoundException("No project found with the id "+projectId); 
		project = projectO.get();
		Integer weeks = request.getWeeks();
		LocalDate startDate = LocalDate.now();
		LocalDate dueDate = startDate.plusWeeks((long)weeks);
		Sprint sprint = new Sprint();
		sprint.setWeeks(weeks);
		sprint.setStartDate(startDate);
		sprint.setEndDate(dueDate);
		sprint.setProject(project);
		return sprintRepo.save(sprint);
	}
	
	
	
	

}
