package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Submission;
import com.github.stackscrubs.stuq.backend.model.jpa.SubmissionId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    Submission findById(SubmissionId id);

    List<Submission> findAll();

    void deleteById(SubmissionId id);
}
