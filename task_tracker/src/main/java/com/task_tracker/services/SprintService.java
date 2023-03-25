package com.task_tracker.services;

import com.task_tracker.entities.Sprint;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.SprintNotfoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.requests.CreateSprintRequest;

public interface SprintService {

	public Sprint createSprint(Long projectId, CreateSprintRequest request) throws ProjectNotFoundException;
	
	public Sprint addTaskToASprint(Long sprintId, Long taskId) throws SprintNotfoundException, TaskNotFoundException;
	
	public Sprint getSprint(Long sprintId) throws SprintNotfoundException;
	
	public Sprint deleteTaskFromSprint(Long sprintId, Long taskId) throws SprintNotfoundException, TaskNotFoundException;
}
