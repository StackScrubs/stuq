package com.github.stackscrubs.stuq.backend.controller;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.CreateSubjectRequest;
import com.github.stackscrubs.stuq.backend.model.jpa.Assignment;
import com.github.stackscrubs.stuq.backend.model.jpa.Subject;
import com.github.stackscrubs.stuq.backend.model.jpa.Teacher;
import com.github.stackscrubs.stuq.backend.model.jpa.TeachingAssistant;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;
import com.github.stackscrubs.stuq.backend.service.SubjectService;

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

@RestController
@RequestMapping("/subject")
@EnableAutoConfiguration
@CrossOrigin(origins = "") //TODO:
public class SubjectController {
    @Autowired
    private SubjectService subjectService;

    @GetMapping(value = "/{term_year}/{term_period}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Subject getById(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getSubject(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/teaching_assistants/{term_year}/{term_period}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Teacher> getTeachers(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getTeachers(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/teachers/{term_year}/{term_period}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TeachingAssistant> getTeachingAssistants(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getTeachingAssistants(new TermId(termYear, termPeriod), code);
    }

    @GetMapping(value = "/assignments/{term_year}/{term_period}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Assignment> getAssignments(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        return this.subjectService.getAssignments(new TermId(termYear, termPeriod), code);
    }
    
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody CreateSubjectRequest request) {
        this.subjectService.create(new TermId(request.getTermYear(), request.getTermPeriod()), request.getCode());
    }

    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody CreateSubjectRequest request) {
        this.subjectService.update(new TermId(request.getTermYear(), request.getTermPeriod()), request.getCode());
    }

    @DeleteMapping(value = "/{term_year}/{term_period}/{code}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(
        @PathVariable int termYear,
        @PathVariable String termPeriod,
        @PathVariable String code
    ) {
        this.subjectService.delete(new TermId(termYear, termPeriod), code);
    }
}
