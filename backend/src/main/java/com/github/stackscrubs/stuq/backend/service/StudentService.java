package com.github.stackscrubs.stuq.backend.service;

import com.github.stackscrubs.stuq.backend.model.StudentNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.repository.StudentRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StudentService handles operations on students stored in the database.
 */
@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    Logger logger = LoggerFactory.getLogger(StudentService.class);

    /**
     * Gets a student by a given ID.
     * @param id The ID of the student to get.
     * @return The student with the given ID.
     * @throws StudentNotFoundException There is no student with the given ID.
     */
    public Student getStudent(int id) {
        return getStudentOrThrow(id);
    }

    /**
     * Helper method for finding a student in the database or throwing an exception if it's not found.
     * @param id The ID of the student to get.
     * @return The student with the given ID.
     * @throws StudentNotFoundException There is no student with the given ID.
     */
    private Student getStudentOrThrow(int id) {
        return this.studentRepository.findById(id)
            .orElseThrow(() -> {
                logger.info("Unable to find student with id=" + id);
                return new StudentNotFoundException();
            });
    }
}
