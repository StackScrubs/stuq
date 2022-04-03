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
}
