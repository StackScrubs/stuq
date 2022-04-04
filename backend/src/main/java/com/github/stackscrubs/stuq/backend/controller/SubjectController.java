package com.github.stackscrubs.stuq.backend.controller;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.CreateSubjectRequest;
import com.github.stackscrubs.stuq.backend.model.Queue;
import com.github.stackscrubs.stuq.backend.model.jpa.Assignment;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.Teacher;
import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.service.QueueService;
import com.github.stackscrubs.stuq.backend.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SubjectController handles requests for the SubjectService.
 */
@RestController
@RequestMapping("/subject")
@EnableAutoConfiguration
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private QueueService queueService;

    @GetMapping(value = "/{termYear}/{termPeriod}/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject getById(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getSubject(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Subject> getAll() {
        return this.subjectService.getSubjects();
    }

    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/teaching-assistants", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getTeachers(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getTeachers(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/teachers", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingAssistant> getTeachingAssistants(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getTeachingAssistants(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/assignments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Assignment> getAssignments(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getAssignments(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/{termYear}/{termPeriod}/{code}/queue", produces = MediaType.APPLICATION_JSON_VALUE)
    public Queue getQueue(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.queueService.getBySubject(new TermId(termYear, termPeriod), code);
    }
    
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CreateSubjectRequest request) {
        this.subjectService.create(new TermId(request.getTermYear(), request.getTermPeriod()), request.getCode(), request.getName());
    }

    @PutMapping(value = "/{termYear}/{termPeriod}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code,
        @RequestBody CreateSubjectRequest request
    ) {
        this.subjectService.update(
            new TermId(termYear, termPeriod),
            code,
            request.getCode(),
            request.getName()
        );
    }

    @DeleteMapping(value = "/{termYear}/{termPeriod}/{code}")
    public void delete(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        this.subjectService.delete(new TermId(termYear, termPeriod), code);
    }
}
