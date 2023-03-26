package com.task_tracker.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.Sprint;
import com.task_tracker.entities.Task;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.SprintNotfoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.repositories.ProjectRepo;
import com.task_tracker.repositories.SprintRepo;
import com.task_tracker.repositories.TaskRepo;
import com.task_tracker.requests.CreateSprintRequest;

@Service
public class SprintServiceImpl implements SprintService{

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private SprintRepo sprintRepo;
	
	@Autowired
	private TaskRepo taskRepo;

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

	@Override
	public Sprint addTaskToASprint(Long sprintId, Long taskId) throws SprintNotfoundException, TaskNotFoundException {
		// TODO Auto-generated method stub
		
		Optional<Sprint> sprintO = sprintRepo.findById(sprintId);
		
		if(sprintO.isPresent()) {
			Sprint sprint = sprintO.get();
			Optional<Task> taskO = taskRepo.findById(taskId);
			if(taskO.isPresent()) {
				Task task = taskO.get();
				sprint.getSprintTasks().add(task);
				task.setSprint(sprint);
				return sprintRepo.save(sprint);
			}
			throw new TaskNotFoundException("Task not found with the id "+taskId);
		}
		
		throw new SprintNotfoundException("No sprint found with the sprintId "+sprintId);
	}

	@Override
	public Sprint getSprint(Long sprintId) throws SprintNotfoundException {
		// TODO Auto-generated method stub
		Optional<Sprint> sprintO = sprintRepo.findById(sprintId);
		if(sprintO.isPresent()) {
			
			return sprintO.get();
			
		}
		throw new SprintNotfoundException("No sprint found with the id "+sprintId);
	}

	@Override
	public Sprint deleteTaskFromSprint(Long sprintId, Long taskId)
			throws SprintNotfoundException, TaskNotFoundException {
		// TODO Auto-generated method stub
		Sprint sprint = getSprintById(sprintId);
		if(sprint == null) throw new SprintNotfoundException("No sprint fount with the id "+sprintId);
		Optional<Task> taskO = taskRepo.findById(taskId);
		if(!taskO.isPresent()) throw new TaskNotFoundException("No task found with the id "+taskId);
		Task task = taskO.get();
		task.setSprint(null);
	  taskRepo.save(task);
	  return sprint;
	}
	
	
	private Sprint getSprintById(Long sprintId) {
		Optional<Sprint> sprintO = sprintRepo.findById(sprintId);
		if(sprintO.isPresent()) return sprintO.get();
		return null;
	}

	@Override
	public List<Task> getAllTasksFromSprint(Long sprintId) throws SprintNotfoundException {
       Optional<Sprint> sprintO = sprintRepo.findById(sprintId);
       if(sprintO.isPresent()) {
    	   return sprintO.get().getSprintTasks();
       }
       throw new SprintNotfoundException("No sprint found with the id "+sprintId);
	}
	
	
	
	
	
	

}
