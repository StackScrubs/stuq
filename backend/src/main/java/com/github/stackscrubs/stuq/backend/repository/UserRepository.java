package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.model.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    
    User findById(int id);

    List<User> findAll();

    void deleteById(int id);

    Optional<User> findByEmail(String email);
}
