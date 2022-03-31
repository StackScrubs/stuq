package com.github.stackscrubs.stuq.backend.repository;

import java.util.List;

import com.github.stackscrubs.stuq.backend.model.jpa.Term;
import com.github.stackscrubs.stuq.backend.model.jpa.TermId;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TermRepository extends JpaRepository<Term, Integer> {
    Term findById(TermId id);

    List<Term> findAll();

    void deleteById(int id);
}
