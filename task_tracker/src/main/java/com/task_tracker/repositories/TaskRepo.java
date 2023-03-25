package com.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_tracker.entities.Task;

public interface TaskRepo extends JpaRepository<Task, Long>{

}
