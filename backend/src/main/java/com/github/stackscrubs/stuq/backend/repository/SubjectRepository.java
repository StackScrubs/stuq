package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, SubjectId> {
    
    Optional<Subject> findById(SubjectId id);

    List<Subject> findAll();

    void deleteById(SubjectId id);
}
