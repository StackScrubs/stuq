package com.github.stackscrubs.stuq.backend.model.jpa;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class Submission {
    
    @EmbeddedId
    @Column(nullable = false)
    private SubmissionId id;

    @Column(nullable = false)
    private boolean isApproved;

    private String feedback;

    public Submission(@NonNull SubmissionId id, @NonNull boolean isApproved, String feedback) {
        this.id = id;
        this.isApproved = isApproved;
        this.feedback = feedback;
    }

    public SubmissionId getId() {
        return this.getId();
    }

    public boolean isApproved() {
        return this.isApproved;
    }

    public String getFeedback() {
        return this.feedback;
    }
}

