package com.github.stackscrubs.stuq.backend.controller;

import java.util.ArrayList;
import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.Submission;
import com.github.stackscrubs.stuq.backend.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * StudentController handles requests for the StudentService.
 */
@RestController
@RequestMapping(value = "/student")
@EnableAutoConfiguration
@CrossOrigin(origins = "") //TODO:
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/submissions", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Submission> getSubmissions(Authentication authentication) {
        Session session = (Session)authentication.getPrincipal();
        int studentId = session.getUser().getId();
        
        return new ArrayList<>(this.studentService.getStudent(studentId).getSubmissions());
    }
}
