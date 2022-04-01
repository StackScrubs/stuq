package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.Submission;
import com.github.stackscrubs.stuq.backend.model.jpa.SubmissionId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, SubmissionId> {
    
    Optional<Submission> findById(SubmissionId id);

    List<Submission> findAll();

    void deleteById(SubmissionId id);
}
