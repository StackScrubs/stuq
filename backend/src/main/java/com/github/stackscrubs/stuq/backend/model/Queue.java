package com.github.stackscrubs.stuq.backend.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;
import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;

public class Queue {
    private Subject subject;

    private List<QueueStudent> queuedStudents;

    public Queue(Subject subject) {
        this.subject = subject;
        this.queuedStudents = new ArrayList<>();
        this.queuedStudents.sort(Comparator.comparing(QueueStudent::getJoinTime));
    }

    private Queue(Subject subject, List<QueueStudent> queuedStudents) {
        this.subject = subject;
        this.queuedStudents = queuedStudents;
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

    public boolean contains(Student student) {
        return this.queuedStudents.stream().filter((s) -> s.getStudent().equals(student)).count() > 0;
    }

    public Queue clone() {
        SubjectId subjectId = this.subject.getId();
        TermId subjectTermId = subjectId.getTerm().getId();

        return new Queue(
            new Subject(
                new SubjectId(
                    subjectId.getCode(),
                    new Term(new TermId(subjectTermId.getYear(), subjectTermId.getPeriod()))),
                this.subject.getName()),
            new ArrayList<>(this.queuedStudents)
        );
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
