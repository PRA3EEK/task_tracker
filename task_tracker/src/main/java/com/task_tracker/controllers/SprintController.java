package com.task_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.entities.Sprint;
import com.task_tracker.exceptions.ProjectNotFoundException;
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
	
	
}
