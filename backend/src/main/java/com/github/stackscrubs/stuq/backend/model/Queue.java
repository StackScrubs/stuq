package com.github.stackscrubs.stuq.backend.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Subject;

public class Queue {
    private Subject subject;

    private List<QueueStudent> queuedStudents;

    public Queue(Subject subject) {
        this.subject = subject;
        this.queuedStudents = new ArrayList<>();
        this.queuedStudents.sort(Comparator.comparing(QueueStudent::getJoinTime));
    }

    public Subject getSubject() {
        return this.subject;
    }

    public void addStudent(QueueStudent student) {
        if (this.queuedStudents.contains(student))
            throw new StudentAlreadyInQueueException();

        this.queuedStudents.add(student);
    }

    public void removeStudent(QueueStudent student) {
        if (!this.queuedStudents.remove(student)) {
            throw new StudentNotFoundInQueueException();
        }
    }

    @Override
    public int hashCode() {
        return this.subject.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Queue other = (Queue) obj;
        if (this.subject == null) {
            if (other.subject != null)
                return false;
        } else if (!this.subject.equals(other.subject))
            return false;
        return true;
    }
}
