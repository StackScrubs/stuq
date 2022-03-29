package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeachingAssistant {
    @Id
    private int id;

    private String firstName;
    
    private String lastName;

    public TeachingAssistant(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
}
