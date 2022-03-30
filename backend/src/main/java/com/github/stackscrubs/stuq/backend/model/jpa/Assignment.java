package com.github.stackscrubs.stuq.backend.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

@Entity
public class Assignment {
    @Id
    @Column()
    private int id;

    @NonNull
    private String name;

    @ManyToOne
    @NonNull
    private Subject subject;

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
