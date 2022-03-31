package com.github.stackscrubs.stuqbackend.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.repository.AssignmentRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AssignmentRepositoryTest {
    @Autowired
    private AssignmentRepository assignmentRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertNotNull(assignmentRepository);
    }

    @Test
    void a() {
        assert(true);
    }
}
