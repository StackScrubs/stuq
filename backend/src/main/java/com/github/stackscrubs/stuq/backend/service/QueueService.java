package com.github.stackscrubs.stuq.backend.service;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;

public class QueueService {
    private static QueueService INSTANCE;

    private List<Queue> activeQueues;

    private QueueService() {}

    public synchronized static QueueService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QueueService();
        }

        return INSTANCE;
    }

    public Queue getBySubjectId(SubjectId subjectId) {
        
    }

    public void create() {

    }

    public void delete() {

    }
}
