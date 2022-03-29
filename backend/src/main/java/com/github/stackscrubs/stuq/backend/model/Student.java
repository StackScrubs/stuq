package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;

@Entity
public class Student extends User {

	public Student( int id,
                    String firstName,
                    String lastName,
                    String email,
                    String phone,
                    String password_hash)
    {
		super(id, firstName, lastName, email, phone, password_hash);
	}
}
