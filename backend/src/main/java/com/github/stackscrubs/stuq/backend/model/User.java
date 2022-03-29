package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    private int id;

    private String firstName;
    
    private String lastName;

    private String email;

    private String phone;

    private String password_hash;

    public User(int id,
                String firstName,
                String lastName,
                String email,
                String phone,
                String password_hash)
    {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password_hash = password_hash;
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
    
    public String getEmail() {
        return this.email;
    }
    
    public String getPhone() {
        return this.phone;
    } 
    
    public String getPasswordHash() {
        return this.password_hash;
    }
}
