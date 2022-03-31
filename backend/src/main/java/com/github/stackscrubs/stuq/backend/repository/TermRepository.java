package com.github.stackscrubs.stuq.backend.repository;

import com.github.stackscrubs.stuq.backend.model.jpa.Term;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Integer> {
    
}
