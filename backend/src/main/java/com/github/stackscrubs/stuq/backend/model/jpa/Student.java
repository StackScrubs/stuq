package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Student extends User {

  @OneToMany(mappedBy = "id.student", fetch = FetchType.LAZY)
  private Set<Submission> submissions;

	Student() {}

  public Student( @NonNull String firstName,
                  @NonNull String lastName,
                  String email,
                  String phone,
                  @NonNull String password_hash)
  	{
		super(firstName, lastName, email, phone, password_hash);
	}

  public Set<Submission> getSubmissions() {
      return this.submissions;
  }

}
