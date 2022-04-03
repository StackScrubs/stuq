package com.github.stackscrubs.stuq.backend.service;

import java.util.ArrayList;

import com.github.stackscrubs.stuq.backend.model.UserRole;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.StudentRepository;
import com.github.stackscrubs.stuq.backend.repository.TeacherRepository;
import com.github.stackscrubs.stuq.backend.repository.TeachingAssistantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeachingAssistantRepository teachingAssistantRepository;

    @Autowired
    private StudentRepository studentRepository;

    public ArrayList<UserRole> getUserRoles(User user) {
        ArrayList<UserRole> roles = new ArrayList<>();
        if (teacherRepository.existsById(user.getId())) {
            roles.add(UserRole.TEACHER);
        }
        if (teachingAssistantRepository.existsById(user.getId())) {
            roles.add(UserRole.TEACHING_ASSISTANT);
        }
        if (studentRepository.existsById(user.getId())) {
            roles.add(UserRole.STUDENT);
        }
        return roles;
    }
}
