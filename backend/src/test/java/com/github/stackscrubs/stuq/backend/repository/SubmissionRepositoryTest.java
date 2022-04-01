package com.github.stackscrubs.stuq.backend.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class SubmissionRepositoryTest {
    @Autowired
    private SubmissionRepository submissionRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(this.submissionRepository);
    }

    /*
        Due to time constraints, testing JPA was not prioritized.
        However, as this is a submission for evaluation, we did write for one:
        Tests for JPA Entities may be found in UserRepositoryTest.java
    */
}
