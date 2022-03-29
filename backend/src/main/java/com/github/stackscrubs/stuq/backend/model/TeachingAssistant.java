package com.github.stackscrubs.stuq.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class TeachingAssistant extends User {

    @ManyToMany(mappedBy = "teaching_assistant", fetch = FetchType.LAZY)
    private Set<Subject> subjects = new HashSet<>();

    public TeachingAssistant(int id,
                            String firstName,
                            String lastName,
                            String email,
                            String phone,
                            String password_hash)
    {
        super(id, firstName, lastName, email, phone, password_hash);
    }
}
