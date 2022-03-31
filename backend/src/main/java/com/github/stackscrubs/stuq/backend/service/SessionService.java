package com.github.stackscrubs.stuq.backend.service;

import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.UserNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.SessionRepository;
import com.github.stackscrubs.stuq.backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

public class SessionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    public Session create(UserCredentials credentials) throws UserNotFoundException {
        User user = userRepository.findByEmail(credentials.getEmail())
            .filter((x) -> x.passwordMatches(credentials.getPassword()))
            .orElseThrow(() -> new UserNotFoundException());

        Session session = new Session(user);
        sessionRepository.save(session);

        return session;
    }
}
