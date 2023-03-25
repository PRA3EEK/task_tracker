package com.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_tracker.entities.Project;

public interface ProjectRepo extends JpaRepository<Project, Long>{

}
