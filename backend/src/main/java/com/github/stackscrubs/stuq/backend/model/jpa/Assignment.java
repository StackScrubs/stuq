package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Assignment {
    @Id
    @Column(nullable = false)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Column(nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    private Set<Submission> submissions;

    public Assignment(@NonNull int id, @NonNull String name, @NonNull Subject subject) {
        this.id = id;
        this.name = name;
        this.subject = subject;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Subject getSubject() {
        return this.subject;
    }
}
