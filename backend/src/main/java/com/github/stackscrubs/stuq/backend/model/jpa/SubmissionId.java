package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

/**
 * JPA specification of an embeddable submission ID.
 */
@Embeddable
public class SubmissionId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", nullable = false)
    private Assignment assignment;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    SubmissionId() {}

    /**
     * Constructor.
     * @param student The student this submission belongs to.
     * @param assignment The assignment this submission is associated with.
     */
    public SubmissionId(@NonNull Student student, @NonNull Assignment assignment) {
        this.student = Objects.requireNonNull(student, "student cannot be null");
        this.assignment = Objects.requireNonNull(assignment, "assignment cannot be null");
    }

    /**
     * Getter for student.
     * @return The student who made this submission.
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Getter for assignment.
     * @return The assignment this submission is associated with.
     */
    public Assignment getAssignment() {
        return this.assignment;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.student, this.assignment);
    }

    /**
     * Override of Object's equals method.
     * @param obj Other object to compare.
     * @return True if the objects are strictly equal, false otherwise.
     */
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
