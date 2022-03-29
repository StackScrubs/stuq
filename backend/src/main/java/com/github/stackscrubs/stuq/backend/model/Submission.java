package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Submission {
    @Id
    @NonNull
    private Student student;

    @Id
    @NonNull
    private Assignment assignment;

    @NonNull
    private boolean isApproved;

    private String feedback;

    public Submission(@NonNull Student student, @NonNull Assignment assignment, @NonNull boolean isApproved, String feedback) {
        this.student = student;
        this.assignment = assignment;
        this.isApproved = isApproved;
        this.feedback = feedback;
    }

    public Student getStudent() {
        return this.student;
    }

    public Assignment getAssignemnt() {
        return this.assignment;
    }

    public boolean isApproved() {
        return this.isApproved;
    }

    public String getFeedback() {
        return this.feedback;
    }
}

