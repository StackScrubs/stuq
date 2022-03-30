package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;

import org.springframework.lang.NonNull;

@Entity
public class Student extends User {

	public Student( @NonNull int id,
                  @NonNull String firstName,
                  @NonNull String lastName,
                  String email,
                  String phone,
                  @NonNull String password_hash)
    {
		super(id, firstName, lastName, email, phone, password_hash);
	}
}
