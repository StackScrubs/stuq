package com.github.stackscrubs.stuq.backend.service;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.CredentialsNotFoundException;
import com.github.stackscrubs.stuq.backend.model.InvalidCredentialsException;
import com.github.stackscrubs.stuq.backend.model.SessionNotFoundException;
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

    public Session create(UserCredentials credentials) {
        User user = userRepository.findByEmail(credentials.getEmail())
            .filter((x) -> x.passwordMatches(credentials.getPassword()))
            .orElseThrow(() -> new InvalidCredentialsException());

        Session session = new Session(user);
        sessionRepository.save(session);

        return session;
    }

    public void delete(byte[] token) {
        Session session = sessionRepository.findById(token)
            .orElseThrow(() -> new SessionNotFoundException());
        
        sessionRepository.delete(session);
    }
}
