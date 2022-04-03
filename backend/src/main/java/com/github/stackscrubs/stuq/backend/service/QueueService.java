package com.github.stackscrubs.stuq.backend.service;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.QueueAlreadyExistsException;
import com.github.stackscrubs.stuq.backend.model.QueueNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private SubjectService subjectService;

    private List<Queue> activeQueues;

    public List<Queue> getActiveQueues() {
        return this.activeQueues;
    }

    public synchronized Optional<Queue> findBySubjectId(SubjectId subjectId) {
        return this.activeQueues.stream()
                .filter((queue) -> queue.getSubject().getId().equals(subjectId))
                .findAny();
    }

    public synchronized void createBySubjectId(SubjectId subjectId) {
        Subject subject = this.subjectService.getSubject(subjectId.getTerm().getId(), subjectId.getCode());
        Queue queue = new Queue(subject);
        
        if (this.activeQueues.contains(queue))
            throw new QueueAlreadyExistsException();

        this.activeQueues.add(queue);
    }

    public synchronized void deleteBySubjectId(SubjectId subjectId) {
        Subject subject = this.subjectService.getSubject(subjectId.getTerm().getId(), subjectId.getCode());
        Queue queue = new Queue(subject);
        
        if (!this.activeQueues.remove(queue)) {
            throw new QueueNotFoundException();
        }
    }
}
