package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a student.
 */
@Entity
public class Student extends User {

	@OneToMany(mappedBy = "id.student", fetch = FetchType.LAZY)
	private Set<Submission> submissions;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
	Student() {}

	/**
	 * Constructor, inherits User's constructor.
	 * @param firstName Student's first name.
	 * @param lastName Student's last name.
	 * @param email Student's email.
	 * @param phone Student's phone.
	 * @param password Student's password to be hashed before inserting into database.
	 */
	public Student( 
		@NonNull String firstName,
		@NonNull String lastName,
		@NonNull String email,
		String phone,
		@NonNull String password
	) {
		super(firstName, lastName, email, phone, password);
	}

	/**
	 * Getter for submissions submitted by the student.
	 * @return Set of submissions submitted by the student.
	 */
	public Set<Submission> getSubmissions() {
		return this.submissions;
	}

}
