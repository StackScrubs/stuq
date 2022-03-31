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
public class SubjectRepositoryTest {
    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void injectedComponentsAreNotNull() {
        assertNotNull(this.subjectRepository);
    }
}
