package com.task_tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.entities.Sprint;
import com.task_tracker.entities.Task;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.SprintNotfoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.requests.CreateSprintRequest;
import com.task_tracker.services.SprintService;

@RestController
@CrossOrigin(origins = "*")
public class SprintController {

	@Autowired
	private SprintService sprintService;
	
	@PostMapping("/sprint/create")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Sprint> createSprintHandler(@RequestParam("projectId") Long projectId, @RequestBody CreateSprintRequest request) throws ProjectNotFoundException{
	 	return new ResponseEntity<Sprint>(sprintService.createSprint(projectId, request), HttpStatus.CREATED);
	}
	
	@PutMapping("/sprint/add/task")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Sprint> addTaskToASprintHandler(@RequestParam("sprintId") Long sprintId, @RequestParam("taskId") Long taskId) throws SprintNotfoundException, TaskNotFoundException{
		return new ResponseEntity<Sprint>(sprintService.addTaskToASprint(sprintId, taskId), HttpStatus.OK);
	}
	
	@GetMapping("/sprint")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Sprint> getSprintHandler(@RequestParam("sprintId") Long sprintId) throws SprintNotfoundException{
		return new ResponseEntity<Sprint>(sprintService.getSprint(sprintId), HttpStatus.FOUND);
	}
	
	@PutMapping("/sprint/delete/task")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Sprint> deleteTaskFromSprintHandler(@RequestParam("sprintId") Long sprintId, @RequestParam("taskId") Long taskId) throws SprintNotfoundException, TaskNotFoundException{
		return new ResponseEntity<Sprint>(sprintService.deleteTaskFromSprint(sprintId, taskId), HttpStatus.OK);
	}
	
	@GetMapping("/sprint/task")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Task>> getAllTasksFromASprintHandler(@RequestParam("sprintId") Long sprintId) throws SprintNotfoundException{
		return new ResponseEntity<List<Task>>(sprintService.getAllTasksFromSprint(sprintId), HttpStatus.FOUND);
	}
	
}
