package com.github.stackscrubs.stuq.backend.repository;

import com.github.stackscrubs.stuq.backend.model.jpa.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    
}
