package com.task_tracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.task_tracker.entities.Task;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.requests.CommentRequest;
import com.task_tracker.requests.CreateTaskRequest;
import com.task_tracker.services.TaskService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/task/create")
	@PreAuthorize("hasRole('USER')")
    public ResponseEntity<Task> createTaskHandler(@RequestParam("projectId") Long projectId, @RequestBody CreateTaskRequest request) throws UserException, ProjectNotFoundException {
        return new ResponseEntity<Task>(taskService.createTask(projectId, request), HttpStatus.CREATED);
	}
	
	@DeleteMapping("/task/delete")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> deleteTaskHandler(@RequestParam("taskId") Long taskId) throws TaskNotFoundException{
		return new ResponseEntity<Task>(taskService.deleteTask(taskId), HttpStatus.OK);
	}
	
	@PutMapping("/task/priority/set")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> updatePriorityHandler(@RequestParam("taskId") Long taskId, @RequestParam("priority") String priority) throws TaskNotFoundException{
	  return new ResponseEntity<Task>(taskService.updatePriority(taskId, priority), HttpStatus.ACCEPTED);	
	}
	
	@PutMapping("/task/type/set")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> updateTypeNameHandler(@RequestParam("taskId") Long taskId, @RequestParam("typeName") String typeName) throws TaskNotFoundException{
		return new ResponseEntity<Task>(taskService.updateTypeName(taskId, typeName), HttpStatus.CREATED);
	}
	
	@PutMapping("/task/status/set")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> updateStatusHandler(@RequestParam("taskId") Long taskId, @RequestParam("status") String status) throws TaskNotFoundException{
		return new ResponseEntity<Task>(taskService.updateStatus(taskId, status), HttpStatus.CREATED);
	}
	
	@PutMapping("/task/duedate/set")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> updateDueDateHandler(@RequestParam("taskId") Long taskId, @RequestParam("dueDate") String dueDate) throws TaskNotFoundException{
		return new ResponseEntity<Task>(taskService.updateDueDate(taskId, dueDate), HttpStatus.CREATED);
	}
	
	@GetMapping("/task")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> getTaskHandler(@RequestParam("taskId") Long taskId) throws TaskNotFoundException{
		return new ResponseEntity<Task>(taskService.getTask(taskId), HttpStatus.FOUND);
	}
	
	@PostMapping("/task/comment")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> addComment(@RequestParam("taskId") Long taskId, @Valid @RequestBody CommentRequest request) throws TaskNotFoundException, UserException{
	 return new ResponseEntity<Task>(taskService.addComment(taskId, request.getComment(), request.getUserId()), HttpStatus.CREATED);	
	}
	
	@PutMapping("/task/update")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Task> updateTask(@RequestBody Task task) throws UserException{
		return new ResponseEntity<Task>(taskService.updateTask(task), HttpStatus.ACCEPTED);
	}
}
