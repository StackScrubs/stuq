package com.github.stackscrubs.stuq.backend.service;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;

import java.util.Optional;

import javax.transaction.Transactional;

import com.github.stackscrubs.stuq.backend.model.InvalidCredentialsException;
import com.github.stackscrubs.stuq.backend.model.SessionNotFoundException;
import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.SessionRepository;
import com.github.stackscrubs.stuq.backend.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * SessionServices handles creation and deletion of user sessions.
 */
@Service
public class SessionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    Logger logger = LoggerFactory.getLogger(SessionService.class);

    /**
     * Creates a session for a user with the specified credentials.
     * @param credentials The user credentials.
     * @return New session for the user.
     * @throws InvalidCredentialsException The credentials were invalid. The username or the password was incorrect.
     */
    public Session create(UserCredentials credentials) {
        logger.debug("Creating session for user " + credentials.getEmail());

        User user = userRepository.findByEmail(credentials.getEmail())
            .filter((x) -> x.passwordMatches(credentials.getPassword()))
            .orElseThrow(() -> { 
                logger.info("Unable to create session for user: Invalid username or password.");
                return new InvalidCredentialsException();
            });

        Session session = new Session(user);
        sessionRepository.save(session);

        logger.debug("Session created for " + credentials.getEmail());

        return session;
    }

    /**
     * Finds and refreshes a session by token.
     * @param token The token of the session to find.
     */
    @Transactional
    public Optional<Session> findAndRefresh(byte[] token) {
        logger.debug("Finding session");
        
        Optional<Session> session = sessionRepository.findById(token);
        session.ifPresent(Session::refresh);

        logger.debug(session.isPresent() ? "Session found" : "Session not found");
        
        return session;
    }

    /**
     * Deletes a session.
     * @param token The token of the session to be deleted.
     * @throws SessionNotFoundException The session with the specified token does not exist.
     */
    public void delete(byte[] token) {
        logger.debug("Deleting session");
        
        Session session = sessionRepository.findById(token)
            .orElseThrow(() -> { 
                logger.warn("Attempted to delete a non-existent session");
                return new SessionNotFoundException();
            });
        
        sessionRepository.delete(session);

        logger.debug("Session deleted");
    }
    
    @Scheduled(fixedDelay = 1000  * 60 * 5) // every 5 minutes
    public void purgeExpired() {
        logger.debug("Cleaning up expired sessions");
        sessionRepository.deleteAllExpired();
    }
}
