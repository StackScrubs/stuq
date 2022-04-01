package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingAssistantRepository extends JpaRepository<TeachingAssistant, Integer> {
    
    TeachingAssistant findById(int id);

    List<TeachingAssistant> findAll();

    void deleteById(int id);
}
