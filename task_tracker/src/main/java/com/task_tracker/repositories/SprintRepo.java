package com.task_tracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_tracker.entities.Sprint;

public interface SprintRepo extends JpaRepository<Sprint, Long>{

}
