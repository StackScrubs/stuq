package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Submission {
    @Id
    private Student student;

    @Id
    private Assignment assignment;

    private boolean is_approved;

    private String feedback;

    public Submission(Student student, Assignment assignment, boolean is_approved, String feedback) {
        this.student = student;
        this.assignment = assignment;
        this.is_approved = is_approved;
        this.feedback = feedback;
    }

    public Student getStudent() {
        return this.student;
    }

    public Assignment getAssignemnt() {
        return this.assignment;
    }

    public boolean isApproved() {
        return this.is_approved;
    }

    public String getFeedback() {
        return this.feedback;
    }
}

