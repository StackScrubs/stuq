package com.github.stackscrubs.stuq.backend.model.jpa;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class Term {
    @EmbeddedId
    @Column(nullable = false)
    private TermId id;

    public Term(@NonNull TermId id) {
        this.id = id;
    }

    public TermId getId() {
        return id;
    }
}
