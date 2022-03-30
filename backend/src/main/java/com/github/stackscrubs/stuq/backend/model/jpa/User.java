package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;

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

    @Override
    public int hashCode() {
        return Objects.hash(this.id,
                            this.firstName,
                            this.lastName,
                            this.email,
                            this.phone,
                            this.password_hash);
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
        if (this.password_hash == null) {
            if (other.password_hash != null)
                return false;
        } else if (!this.password_hash.equals(other.password_hash))
            return false;
        if (this.phone == null) {
            if (other.phone != null)
                return false;
        } else if (!this.phone.equals(other.phone))
            return false;
        return true;
    }
}
