package com.github.stackscrubs.stuq.backend.service;

import static org.junit.jupiter.api.Assertions.*;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.UserRepository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SessionServiceTest {
    @Autowired
    SessionService sessionService;

    @Autowired
    UserRepository userRepository;

    User user;

    @Test
    public void createSession_validCredentials_returnsSessionForTheUser() {
        user = userRepository.saveAndFlush(new Student("Zyzz", "The Great", "zyzz@bulk.org", null, ""));

        UserCredentials credentials = new UserCredentials("zyzz@bulk.org", "ILoveTests!");

        Session session = sessionService.create(credentials);

        assertEquals(user, session.getUser());
        assertNotNull(session.getToken());
    }
}
