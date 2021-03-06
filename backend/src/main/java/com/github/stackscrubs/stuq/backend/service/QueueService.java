package com.github.stackscrubs.stuq.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.QueueAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.QueueNotFoundException;
import com.github.stackscrubs.stuq.backend.model.QueueStudent;
import com.github.stackscrubs.stuq.backend.model.StudentNotFoundInQueueException;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;
import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * QueueService handles student queues for specific subjects,
 * allowing for the creation and deletion of such queues,
 * as well as adding and removing students from these queues.
 * 
 * The handling of queues is done purely in-memory and all
 * operations must therefore be thread-safe.
 */
@Service
public class QueueService {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    private List<Queue> activeQueues = new ArrayList<>();

    Logger logger = LoggerFactory.getLogger(QueueService.class);

    /**
     * Gets all active queues.
     * @return List of all active queues.
     */
    public List<Queue> getActiveQueues() {
        return this.activeQueues;
    }

    /**
     * Gets the queue for a subject with a given subject code that takes place during the given term.
     * Thread-safe as the list of active queues are solely stored in memory.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCodeThe subject's code.
     * @return Queue for the subject.
     * @throws QueueNotFoundException The subject has no active queue.
     */
    public synchronized Queue getBySubject(TermId termId, String subjectCode) {
        logger.debug("Finding queue");

        return this.findQueueOrThrow(termId, subjectCode).clone();
    }

    /**
     * Adds a student to a subject's queue.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCodeThe subject's code.
     * @param studentId The ID of the student to add to queue.
     * @throws QueueNotFoundException The subject has no active queue.
     * @throws StudentNotFoundException The student with the given ID was not found.
     */
    public synchronized void addStudentToQueue(TermId termId, String subjectCode, int studentId) {
        Queue queue = this.findQueueOrThrow(termId, subjectCode);
        Student student = this.studentService.getStudent(studentId);

        queue.addStudent(new QueueStudent(student));
    }

    /**
     * Removes a student from a queue.
     * @param studentId The student to remove from the queue.
     * @throws StudentNotFoundException The student with the given ID was not found.
     * @throws StudentNotFoundInQueueException The student is not currently in a queue.
     */
    public synchronized void removeStudentFromQueue(int studentId) {
        Student student = this.studentService.getStudent(studentId);

        Queue queue = this.activeQueues.stream()
                        .filter((q) -> q.contains(student))
                        .findAny()
                        .orElseThrow(() -> {
                            logger.info("Unable to find student in queue");
                            return new StudentNotFoundInQueueException();
                        });

        queue.removeStudent(new QueueStudent(student));
    }

    /**
     * Creates a new active queue for the given subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCodeThe subject's code.
     * @throws QueueAlreadyExistsException The queue is already active.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public synchronized void createBySubject(TermId termId, String subjectCode) {
        logger.debug("Creating queue");
        
        Subject subject = this.subjectService.getSubject(termId, subjectCode);
        Queue queue = new Queue(subject);
        
        if (this.activeQueues.contains(queue)) {
            logger.info("Unable to create queue for subject with term year="
                        + termId.getYear() + ", term period="
                        + termId.getPeriod() + " and code="
                        + subjectCode);

            throw new QueueAlreadyExistsException();
        }

        logger.debug("Created queue");
        this.activeQueues.add(queue);
    }

    /**
     * Deletes a queue from the list of active queues given its subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCodeThe subject's code.
     * @throws QueueNotFoundException The queue is not currently active.
     * @throws TermNotFoundException The term with the given ID is not registered in the database.
     * @throws SubjectNotFoundException The subject code is not registered in the database.
     */
    public synchronized void deleteBySubject(TermId termId, String subjectCode) {
        logger.debug("Deleting queue");

        Subject subject = this.subjectService.getSubject(termId, subjectCode);
        Queue queue = new Queue(subject);
        
        if (!this.activeQueues.remove(queue)) {
            logger.info("Unable to delete queue for subject with term year="
                        + termId.getYear() + ", term period="
                        + termId.getPeriod() + " and code="
                        + subjectCode);

            throw new QueueNotFoundException();
        }

        logger.debug("Deleted queue");
    }

    /**
     * Helper method for finding an active queue given its subject.
     * @param termId The ID of the term in which the subject takes place.
     * @param subjectCodeThe subject's code.
     * @return The active queue of the given subject.
     * @throws QueueNotFoundException The queue is not currently active.
     */
    private synchronized Queue findQueueOrThrow(TermId termId, String subjectCode) {
        return this.activeQueues.stream()
            .filter((queue) ->
                queue
                    .getSubject()
                    .getId()
                    .equals(new SubjectId(subjectCode, new Term(termId)))
            )
            .findAny()
            .orElseThrow(() -> {
                logger.info("Unable to find queue for subject with term year="
                            + termId.getYear() + ", term period="
                            + termId.getPeriod() + " and code="
                            + subjectCode);

                return new QueueNotFoundException();
            });
    }
}
