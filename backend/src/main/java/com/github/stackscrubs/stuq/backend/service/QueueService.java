package com.github.stackscrubs.stuq.backend.service;

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

@Service
public class QueueService {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    private List<Queue> activeQueues;

    Logger logger = LoggerFactory.getLogger(QueueService.class);

    public List<Queue> getActiveQueues() {
        return this.activeQueues;
    }

    public synchronized Queue getBySubject(TermId termId, String subjectCode) {
        logger.debug("Finding queue");

        return this.findQueueOrThrow(termId, subjectCode);
    }

    public synchronized void addStudentToQueue(TermId termId, String subjectCode, int studentId) {
        Queue queue = this.findQueueOrThrow(termId, subjectCode);
        Student student = this.studentService.getStudent(studentId);

        queue.addStudent(new QueueStudent(student));
    }

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
