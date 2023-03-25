package com.task_tracker.services;

import com.task_tracker.entities.Task;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.requests.CreateTaskRequest;

public interface TaskService {

	
	public Task createTask(Long projectId, CreateTaskRequest request) throws UserException, ProjectNotFoundException;
	
	public Task deleteTask(Long taskId) throws TaskNotFoundException;
	
	public Task updatePriority(Long taskId, String priority) throws TaskNotFoundException;
	
	public Task updateTypeName(Long taskId, String typeName) throws TaskNotFoundException;
	
	public Task updateStatus(Long taskId, String status) throws TaskNotFoundException;
	
	public Task updateDueDate(Long taskId, String date) throws TaskNotFoundException;
	
	public Task getTask(Long taskId) throws TaskNotFoundException;
	
}
