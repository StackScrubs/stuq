package com.github.stackscrubs.stuq.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.model.InvalidCredentialsException;
import com.github.stackscrubs.stuq.backend.model.SessionNotFoundException;
import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.SessionRepository;
import com.github.stackscrubs.stuq.backend.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class SessionServiceTest {
    @Autowired
    SessionService sessionService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    UserRepository userRepository;

    User user;

    @BeforeEach
    public void dbSetup() {
        user = userRepository.saveAndFlush(new Student("Zyzz", "The Great", "zyzz@bulk.org", null, "ILoveTests!"));
    }

    @Test
    public void createSession_validCredentials_returnsSessionForTheUser() {
        UserCredentials credentials = new UserCredentials("zyzz@bulk.org", "ILoveTests!");

        Session session = sessionService.create(credentials);

        assertEquals(user, session.getUser());
        assertNotNull(session.getToken());
    }

    @Test
    public void createSession_invalidPassword_throwsInvalidCredentialsError() {
        UserCredentials credentials = new UserCredentials("zyzz@bulk.org", "heyooo");

        assertThrows(InvalidCredentialsException.class, () -> sessionService.create(credentials));
    }

    @Test
    public void createSession_invalidUsername_throwsInvalidCredentialsError() {
        UserCredentials credentials = new UserCredentials("wrong@email.com", "ILoveTests!");

        assertThrows(InvalidCredentialsException.class, () -> sessionService.create(credentials));
    }

    @Test
    public void deleteSession_validToken_removesSession() {
        UserCredentials credentials = new UserCredentials("zyzz@bulk.org", "ILoveTests!");

        Session session = sessionService.create(credentials);
        byte[] token = session.getToken();
        sessionService.delete(token);

        assertTrue(sessionRepository.findById(token).isEmpty());
    }

    @Test
    public void deleteSession_invalidToken_throwsNotFoundException() {
        assertThrows(SessionNotFoundException.class, () -> sessionService.delete(new byte[Session.TOKEN_SIZE]));
    }
}
