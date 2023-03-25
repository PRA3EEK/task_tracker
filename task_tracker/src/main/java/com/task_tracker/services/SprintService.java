package com.task_tracker.services;

import com.task_tracker.entities.Sprint;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.requests.CreateSprintRequest;

public interface SprintService {

	public Sprint createSprint(Long projectId, CreateSprintRequest request) throws ProjectNotFoundException;
	
}
