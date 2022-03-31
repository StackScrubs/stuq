package com.github.stackscrubs.stuq.backend.service;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.jpa.Session;

import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Session login(UserCredentials request) throws Exception {
        
    }

}
