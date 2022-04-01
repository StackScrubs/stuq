package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

import org.springframework.lang.NonNull;

@Entity
public class TeachingAssistant extends User {

    @ManyToMany(mappedBy = "teachingAssistants", fetch = FetchType.LAZY)
    private Set<Subject> subjects = new HashSet<>();

    TeachingAssistant() {}

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
