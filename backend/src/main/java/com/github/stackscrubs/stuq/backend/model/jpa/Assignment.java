package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Assignment {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @Column(nullable = false)
    private Subject subject;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<SubmissionId> submissions;

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

    public Set<SubmissionId> getSubmissions() {
        return this.submissions;
    }
}
