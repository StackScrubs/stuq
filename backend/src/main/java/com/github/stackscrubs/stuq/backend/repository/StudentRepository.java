package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Student;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Student findById(int id);

    List<Student> findAll();

    void deleteById(int id);
}
