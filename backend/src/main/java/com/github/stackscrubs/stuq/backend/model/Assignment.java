package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Assignment {
    @Id
    private int id;

    private String name;

    @ManyToOne
    private Subject subject;

    public Assignment(int id, String name, Subject subject) {
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
