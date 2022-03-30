package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Student extends User {

  @OneToMany(fetch = FetchType.LAZY)
  private Set<SubmissionId> submissions;

	public Student( @NonNull int id,
                  @NonNull String firstName,
                  @NonNull String lastName,
                  String email,
                  String phone,
                  @NonNull String password_hash)
  	{
		super(id, firstName, lastName, email, phone, password_hash);
	}

  public Set<SubmissionId> getSubmissions() {
      return this.submissions;
  }

}
