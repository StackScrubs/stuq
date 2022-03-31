package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<User, byte[]> {
    
    Optional<User> findById(byte[] token);

    List<User> findAll();

    void deleteById(byte[] token);
}
