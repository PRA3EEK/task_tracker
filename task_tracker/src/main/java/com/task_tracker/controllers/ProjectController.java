package com.task_tracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.entities.Project;
import com.task_tracker.services.ProjectService;

@RestController
@CrossOrigin(origins = "*")
public class ProjectController {
	@Autowired
	private ProjectService projectService;

	@PostMapping("project/add")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<Project> createProjectHandler(@RequestParam("title") String title){
		return new ResponseEntity<Project>(projectService.createProject(title), HttpStatus.CREATED);
	}
	
	@GetMapping("project/all")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<List<Project>> getAllProjectsOfAUserHandler(){
		return new ResponseEntity<List<Project>>(projectService.getAllProjectsOfAUser(), HttpStatus.FOUND);
	}
	
	@DeleteMapping("project/delete")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Project> deleteProjectHandler(@RequestParam("projectId") Long projectId){
		return new ResponseEntity<Project>(projectService.deleteProject(projectId), HttpStatus.OK);
	}
}