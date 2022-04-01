package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;
import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, byte[]> {
    
    Optional<Session> findById(byte[] token);

    List<Session> findAll();

    void deleteById(byte[] token);
}
