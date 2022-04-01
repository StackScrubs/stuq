package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.github.stackscrubs.stuq.backend.model.PasswordEncoder;

import org.springframework.lang.NonNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "`user`")
public class User {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column
    private String phone;

    @Column(nullable = false)
    private String passwordHash;

    User() {}

    protected User( 
        @NonNull String firstName,
        @NonNull String lastName,
        @NonNull String email,
        String phone,
        @NonNull String password
    ) {
        this.firstName = Objects.requireNonNull(firstName, "firstName cannot be null");
        this.lastName = Objects.requireNonNull(lastName, "lastName cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        this.phone = phone;
        this.passwordHash = PasswordEncoder.getInstance().encode(Objects.requireNonNull(password, "password cannot be null"));
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return this.phone;
    } 
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.passwordHash = PasswordEncoder.getInstance().encode(password);
    }

    public boolean passwordMatches(String password) {
        return PasswordEncoder.getInstance().matches(password, this.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.id,
            this.firstName,
            this.lastName,
            this.email,
            this.phone,
            this.passwordHash
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (this.email == null) {
            if (other.email != null)
                return false;
        } else if (!this.email.equals(other.email))
            return false;
        if (this.firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!this.firstName.equals(other.firstName))
            return false;
        if (this.id != other.id)
            return false;
        if (this.lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!this.lastName.equals(other.lastName))
            return false;
        if (this.passwordHash == null) {
            if (other.passwordHash != null)
                return false;
        } else if (!this.passwordHash.equals(other.passwordHash))
            return false;
        if (this.phone == null) {
            if (other.phone != null)
                return false;
        } else if (!this.phone.equals(other.phone))
            return false;
        return true;
    }
}
