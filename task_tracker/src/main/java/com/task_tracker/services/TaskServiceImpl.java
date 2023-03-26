package com.task_tracker.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task_tracker.entities.Project;
import com.task_tracker.entities.Task;
import com.task_tracker.entities.User;
import com.task_tracker.exceptions.ProjectNotFoundException;
import com.task_tracker.exceptions.TaskNotFoundException;
import com.task_tracker.exceptions.UserException;
import com.task_tracker.repositories.ProjectRepo;
import com.task_tracker.repositories.TaskRepo;
import com.task_tracker.repositories.UserRepo;
import com.task_tracker.requests.CreateTaskRequest;


@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private GetPrinciple getPrinciple;
	
	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TaskRepo taskRepo;
	
	@Override
	public Task createTask(Long projectId, CreateTaskRequest request) throws UserException, ProjectNotFoundException {
		// TODO Auto-generated method stub
	
		Optional<Project> projectO = projectRepo.findById(projectId);
		
		if(projectO.isPresent()) {
			Project project = projectO.get();
			Long assignee = null;
			
			if(request.getAssignee() == null) assignee = getPrinciple.getLoggedInUserId();
			else assignee = request.getAssignee();
			Optional<User> userO = userRepo.findById(assignee);
			if(userO.isPresent()) {			
				Task task = new Task();
				task.setAssignee(assignee);
				task.setTitle(request.getTitle());
				task.setDescription(request.getDescription());
				task.setDueDate(stringDateParser(request.getDueDate()));
				task.setPriority(request.getPriority());
				task.setStartDate(stringDateParser(request.getStartDate()));
				task.setStatus(request.getStatus());
				task.setTypeName(request.getTypeName());
				project.getTasks().add(task);
				task.setProject(project);
				return taskRepo.save(task);
			}
			 throw new UserException("No user found with the id "+request.getAssignee());
		}
		throw new ProjectNotFoundException("No existing project found with the id "+projectId);
	}

	private LocalDate stringDateParser(String strDate) {
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate date = LocalDate.parse(strDate, format);
		
		return date;
	}

	@Override
	public Task deleteTask(Long taskId) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Task task = getTaskById(taskId);
		Task temp = task;
		if(task == null) throw new TaskNotFoundException("No task found with the id "+taskId);
		taskRepo.delete(task);
		return temp;
	}

	@Override
	public Task updatePriority(Long taskId, String priority) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Task task = getTaskById(taskId);
		if(task == null) throw new TaskNotFoundException("No task found with the id "+taskId);
		task.setPriority(priority);
	    return taskRepo.save(task);
	}

	@Override
	public Task updateTypeName(Long taskId, String typeName) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Task task = getTaskById(taskId);
		if(task == null) throw new TaskNotFoundException("No task found with the id "+taskId);
		task.setTypeName(typeName);
		return taskRepo.save(task);
	}

	@Override
	public Task updateStatus(Long taskId, String status) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Task task = getTaskById(taskId);
		if(task == null) throw new TaskNotFoundException("No task found with the id "+taskId);
		task.setStatus(status);
		return taskRepo.save(task);
	}

	@Override
	public Task updateDueDate(Long taskId, String date) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Task task = getTaskById(taskId);
		if(task == null) throw new TaskNotFoundException("No task found with the id "+taskId);
		task.setDueDate(stringDateParser(date));
		return taskRepo.save(task);
	}
	
 
	public Task getTaskById(Long id) {
		if(!taskRepo.findById(id).isPresent()) return null;
		return taskRepo.findById(id).get();
	}

	@Override
	public Task getTask(Long taskId) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		Optional<Task> taskO = taskRepo.findById(taskId);
		if(taskO.isPresent()) return taskO.get();
		throw new TaskNotFoundException("No task found with the id "+taskId);
	}

	@Override
	public Task addComment(Long taskId, String comment, Long userId) throws TaskNotFoundException, UserException {
		Optional<Task> taskO = taskRepo.findById(taskId);
		if(taskO.isPresent()) {
			Task task = taskO.get();
			Optional<User> userO =  userRepo.findById(userId);
			if(userO.isPresent()) {
				task.getComments().put(comment, userId);
				return taskRepo.save(task);
			}
			throw new UserException("No user found with the id "+userId);
		}
		throw new TaskNotFoundException("No task found with the id "+taskId);
	}

	@Override
	public Task updateTask(Task task) throws UserException {
		Long assignee = task.getAssignee();
		Optional<User> userO = userRepo.findById(assignee);
		if(userO.isPresent())
		return taskRepo.save(task);
		throw new UserException("User not found!");
	}

	
	
}
