package com.github.stackscrubs.stuq.backend.repository;

import com.github.stackscrubs.stuq.backend.model.jpa.Teacher;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    
}
