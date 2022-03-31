package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Embeddable
public class SubmissionId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Assignment assignment;

    SubmissionId() {}

    public SubmissionId(@NonNull Student student, @NonNull Assignment assignment) {
        this.student = student;
        this.assignment = assignment;
    }

    public Student getStudent() {
        return this.student;
    }

    public Assignment getAssignment() {
        return this.assignment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.assignment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SubmissionId other = (SubmissionId) obj;
        if (this.assignment == null) {
            if (other.assignment != null)
                return false;
        } else if (!this.assignment.equals(other.assignment))
            return false;
        if (this.student == null) {
            if (other.student != null)
                return false;
        } else if (!this.student.equals(other.student))
            return false;
        return true;
    }
}
