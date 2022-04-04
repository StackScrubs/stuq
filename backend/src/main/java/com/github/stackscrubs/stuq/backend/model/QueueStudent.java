package com.github.stackscrubs.stuq.backend.model;

import java.time.Instant;
import java.util.Objects;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;

/**
 * Class for representing a queued student,
 * containing the student that is queued
 * and the instant at which they joined the queue.
 */
public class QueueStudent {
    private Student student;

    private Instant joinTime;

    /**
     * Constructor.
     * @param student The student that is queued.
     */
    public QueueStudent(Student student) {
        this.student = Objects.requireNonNull(student, "student cannot be null");
        this.joinTime = Instant.now();
    }

    /**
     * Getter for student.
     * @return The student that is queued.
     */
    public Student getStudent() {
        return this.student;
    }

    /**
     * Getter for join time.
     * @return The instant at which the student joined the queue.
     */
    public Instant getJoinTime() {
        return this.joinTime;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return this.student.hashCode();
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
        QueueStudent other = (QueueStudent) obj;
        if (this.student == null) {
            if (other.student != null)
                return false;
        } else if (!this.student.equals(other.student))
            return false;
        return true;
    }
}
