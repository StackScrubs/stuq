package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a teaching assistant.
 */
@Entity
public class TeachingAssistant extends User {

    @ManyToMany(mappedBy = "teachingAssistants", fetch = FetchType.LAZY)
    private Set<Subject> subjects = new HashSet<>();

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    TeachingAssistant() {}

	/**
	 * Constructor, inherits User's constructor.
	 * @param firstName Teaching assistant's first name.
	 * @param lastName Teaching assistant's last name.
	 * @param email Teaching assistant's email.
	 * @param phone Teaching assistant's phone.
	 * @param password Teaching assistant's password to be hashed before inserting into database.
	 */
    public TeachingAssistant(
		@NonNull String firstName,
		@NonNull String lastName,
		@NonNull String email,
		String phone,
		@NonNull String password
	) {
		super(firstName, lastName, email, phone, password);
	}
}
