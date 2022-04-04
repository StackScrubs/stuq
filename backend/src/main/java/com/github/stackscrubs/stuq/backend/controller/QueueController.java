package com.github.stackscrubs.stuq.backend.controller;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.jpa.SubjectId;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.service.QueueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * QueueController handles requests for the QueueService.
 */
@RestController
@RequestMapping("/queue")
@EnableAutoConfiguration
public class QueueController {
    
    @Autowired
    private QueueService queueService;

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Queue> getAll() {
        return this.queueService.getActiveQueues();
    }

    @PostMapping(value = "/add/{studentId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addToQueue(@PathVariable int studentId, @RequestBody SubjectId subjectId) {
        this.queueService.addStudentToQueue(subjectId.getTerm().getId(), subjectId.getCode(), studentId);
    }

    @DeleteMapping(value = "/remove/{studentId}")
    public void removeFromQueue(@PathVariable int studentId) {
        this.queueService.removeStudentFromQueue(studentId);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody SubjectId subjectId) {
        this.queueService.createBySubject(subjectId.getTerm().getId(), subjectId.getCode());
    }

    @DeleteMapping(value = "/{termYear}/{termPeriod}/{code}")
    public void delete(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        this.queueService.deleteBySubject(new TermId(termYear, termPeriod), code);
    }
}
