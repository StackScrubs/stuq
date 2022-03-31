package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachingAssistantRepository extends JpaRepository<TeachingAssistant, Integer> {
    
    Optional<TeachingAssistant> findById(int id);

    List<TeachingAssistant> findAll();

    void deleteById(int id);
}
