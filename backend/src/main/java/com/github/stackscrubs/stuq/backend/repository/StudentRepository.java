package com.github.stackscrubs.stuq.backend.repository;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    
}
