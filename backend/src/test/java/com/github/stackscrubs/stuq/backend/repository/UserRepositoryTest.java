package com.github.stackscrubs.stuq.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import com.github.stackscrubs.stuq.backend.StuqBackendApplication;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.model.jpa.Student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = StuqBackendApplication.class)
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertNotNull(this.userRepository);
    }
    
    @Test
    public void save_saveNewUser_DoesNotThrow() {
        User user = (User) new Student("John", "Doe", null, "98989898", "_");
        User userSaved = this.userRepository.save(user);
    }

    @Test
    public void getById_getExistingUser_doesNotThrow() {
        int userId = this.userRepository.save((User) new Student("test", "test", null, null, "-")).getId();
        User user = this.userRepository.getById(userId);
    }

    @Test
    public void deleteById_deleteUser_doesNotThrow() {
        int userId = this.userRepository.save((User) new Student("test", "test", null, null, "-")).getId();
        this.userRepository.deleteById(userId);
    }

    @Test
    public void save_saveExistingUser_overridesUser() {
        String initialName = "Johnb";
        String fixedName = "John";

        User user = (User) new Student(initialName, "Doe", null, "98989898", "_");

        user = this.userRepository.save(user);
        user.setFirstName(fixedName);

        this.userRepository.save(user);

        String savedName = this.userRepository.getById(user.getId()).getFirstName();

        assertEquals(fixedName, savedName);
    }

    @Test
    public void findAll_findAllUsers_getsAllUsers() {
        List<User> users = new ArrayList<User>();

        final int USERS_TO_ADD = 10;

        for (int i = 0; i < USERS_TO_ADD; i++)
            users.add((User) new Student("Test1", "Test2", null, null, "s"));

        this.userRepository.saveAll(users);

        assertEquals(USERS_TO_ADD, this.userRepository.findAll().size());
    }
}
