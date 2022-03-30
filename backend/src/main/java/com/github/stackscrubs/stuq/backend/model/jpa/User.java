package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @NonNull
    private int id;

    @NonNull
    private String firstName;
    
    @NonNull
    private String lastName;

    private String email;

    private String phone;

    @NonNull
    private String password_hash;

    protected User(@NonNull int id,
                @NonNull String firstName,
                @NonNull String lastName,
                String email,
                String phone,
                @NonNull String password_hash)
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
