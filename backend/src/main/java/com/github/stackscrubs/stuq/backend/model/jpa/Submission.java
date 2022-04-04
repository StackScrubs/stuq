package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a submission.
 */
@Entity
public class Submission {
    
    @EmbeddedId
    @Column(nullable = false)
    private SubmissionId id;

    @Column(nullable = false)
    private boolean isApproved;

    private String feedback;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    Submission() {}

    /**
     * Constructor.
     * @param id The submission's ID.
     * @param isApproved Whether or not this submission has been approved.
     * @param feedback Optional feedback to the student.
     */
    public Submission(@NonNull SubmissionId id, @NonNull boolean isApproved, String feedback) {
        this.id = Objects.requireNonNull(id, "id cannnot be null");
        this.isApproved = Objects.requireNonNull(isApproved, "isApproved cannot be null");
        this.feedback = feedback;
    }

    /**
     * Getter for ID.
     * @return This submission's ID.
     */
    public SubmissionId getId() {
        return this.getId();
    }

    /**
     * Getter for isApproved.
     * @return True if this submission has been approved, false otherwise.
     */
    public boolean isApproved() {
        return this.isApproved;
    }

    /**
     * Getter for feedback.
     * @return Feedback given to the student.
     */
    public String getFeedback() {
        return this.feedback;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.isApproved, this.feedback);
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
        Submission other = (Submission) obj;
        if (this.feedback == null) {
            if (other.feedback != null)
                return false;
        } else if (!this.feedback.equals(other.feedback))
            return false;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.isApproved != other.isApproved)
            return false;
        return true;
    }
}

