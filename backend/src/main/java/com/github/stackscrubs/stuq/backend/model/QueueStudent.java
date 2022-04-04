package com.github.stackscrubs.stuq.backend.model;

import java.time.Instant;
import java.util.Objects;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;

public class QueueStudent {
    private Student student;

    private Instant joinTime;

    public QueueStudent(Student student) {
        this.student = Objects.requireNonNull(student, "student cannot be null");
        this.joinTime = Instant.now();
    }

    public Student getStudent() {
        return student;
    }

    public Instant getJoinTime() {
        return joinTime;
    }

    @Override
    public int hashCode() {
        return student.hashCode();
    }

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
