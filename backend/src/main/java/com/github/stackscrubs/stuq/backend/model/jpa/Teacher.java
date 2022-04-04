package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a teacher.
 */
@Entity
public class Teacher extends User {

    @ManyToMany(mappedBy = "teachers", fetch = FetchType.LAZY)
    private Set<Subject> subjects = new HashSet<>();

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    Teacher() {}

	/**
	 * Constructor, inherits User's constructor.
	 * @param firstName Teacher's first name.
	 * @param lastName Teacher's last name.
	 * @param email Teacher's email.
	 * @param phone Teacher's phone.
	 * @param password Teacher's password to be hashed before inserting into database.
	 */
    public Teacher(
        @NonNull String firstName,
		@NonNull String lastName,
		@NonNull String email,
		String phone,
		@NonNull String password
	) {
		super(firstName, lastName, email, phone, password);
	}
}
