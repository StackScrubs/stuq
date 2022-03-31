package com.github.stackscrubs.stuq.backend.repository;

import com.github.stackscrubs.stuq.backend.model.jpa.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
    
}
