package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Term {
    @EmbeddedId
    @Column(nullable = false)
    private TermId id;

    @OneToMany(mappedBy = "id.term", fetch = FetchType.LAZY)
    private Set<Subject> subjects;

    public Term(@NonNull TermId id) {
        this.id = id;
    }

    public TermId getId() {
        return this.id;
    }
}
