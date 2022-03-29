package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;

@Entity
public class TeachingAssistant extends User {

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
