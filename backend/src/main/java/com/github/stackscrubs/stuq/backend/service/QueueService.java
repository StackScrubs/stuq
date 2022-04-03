package com.github.stackscrubs.stuq.backend.service;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.QueueAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.QueueNotFoundException;
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

    private List<Queue> activeQueues;

    Logger logger = LoggerFactory.getLogger(QueueService.class);

    public List<Queue> getActiveQueues() {
        return this.activeQueues;
    }

    public synchronized Queue getBySubject(TermId termId, String subjectCode) {
        logger.debug("Finding queue");

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
}
