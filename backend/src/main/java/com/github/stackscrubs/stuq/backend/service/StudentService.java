package com.github.stackscrubs.stuq.backend.service;

import com.github.stackscrubs.stuq.backend.model.StudentNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    public Student getStudent(int id) {
        return getStudentOrThrow(id);
    }

    private Student getStudentOrThrow(int id) {
        return this.studentRepository.findById(id)
            .orElseThrow(() -> {
                logger.info("Unable to find student with id=" + id);
                return new StudentNotFoundException();
            });
    }
}
