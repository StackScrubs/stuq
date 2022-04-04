package com.github.stackscrubs.stuq.backend.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;
import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;

/**
 * Class used to represent an active student queue.
 */
public class Queue {
    private Subject subject;

    private List<QueueStudent> queuedStudents;

    /**
     * Constructor.
     * @param subject The subject this is a queue for.
     */
    public Queue(Subject subject) {
        this.subject = subject;
        this.queuedStudents = new ArrayList<>();
        this.queuedStudents.sort(Comparator.comparing(QueueStudent::getJoinTime));
    }

    /**
     * Copy-constructor.
     * Private to ensure copying of Queue objects are done properly through
     * its clone() method.
     * @param subject The subject this is a queue for.
     * @param queuedStudents Students that are queued in this queue.
     */
    private Queue(Subject subject, List<QueueStudent> queuedStudents) {
        this.subject = subject;
        this.queuedStudents = queuedStudents;
    }

    /**
     * Getter for subject.
     * @return The subject this is a queue for.
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Adds a student to the queue.
     * @param student Student to add to the queue.
     */
    public void addStudent(QueueStudent student) {
        if (this.queuedStudents.contains(student))
            throw new StudentAlreadyInQueueException();

        this.queuedStudents.add(student);
    }

    /**
     * Removes a student from the queue.
     * @param student The student to remove from the queue.
     */
    public void removeStudent(QueueStudent student) {
        if (!this.queuedStudents.remove(student)) {
            throw new StudentNotFoundInQueueException();
        }
    }

    /**
     * Checks whether or not a given student is in the queue.
     * @param student Student to check whether or not exists in the queue.
     * @return True if the student is in the queue, false otherwise.
     */
    public boolean contains(Student student) {
        return this.queuedStudents.stream().filter((s) -> s.getStudent().equals(student)).count() > 0;
    }

    /**
     * Method used to safely deep clone a Queue object.
     */
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

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return this.subject.hashCode();
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
        Queue other = (Queue) obj;
        if (this.subject == null) {
            if (other.subject != null)
                return false;
        } else if (!this.subject.equals(other.subject))
            return false;
        return true;
    }
}
