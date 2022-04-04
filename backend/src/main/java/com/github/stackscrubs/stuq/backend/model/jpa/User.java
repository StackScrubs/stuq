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

/**
 * JPA Entity specification of a user.
 */
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

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    User() {}

    /**
     * Constructor.
     * Protected as no single instance of a user should ever exist by itself.
	 * @param firstName User's first name.
	 * @param lastName User's last name.
	 * @param email User's email.
	 * @param phone User's phone.
	 * @param password User's password to be hashed before inserting into database.
     */
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

    /**
     * Getter for ID.
     * @return The user's ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for first name.
     * @return The user's first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    
    /**
     * Setter for first name.
     * Sets the user's first name.
     * @param firstName First name to give to the user.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for last name.
     * @return The user's last name.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Setter for last name.
     * Sets the user's last name.
     * @param lastName Last name to give to the user.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * Getter for email.
     * @return The user's email.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Setter for email.
     * Sets the user's email.
     * @param email Email to give to the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Getter for phone.
     * @return The user's phone number.
     */
    public String getPhone() {
        return this.phone;
    } 
    
    /**
     * Setter for phone.
     * Sets the user's phone number.
     * @param phone Phone number to give to the user.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Setter for passwordHash.
     * Sets the user's passwordHash.
     * @param password Password to be hashed and given to user.
     */
    public void setPassword(String password) {
        this.passwordHash = PasswordEncoder.getInstance().encode(password);
    }

    /**
     * Method for checking whether a user's password matches the argument string.
     * @param password Password to check equality of.
     * @return True if the set password matches the argument password, false otherwise.
     */
    public boolean passwordMatches(String password) {
        return PasswordEncoder.getInstance().matches(password, this.passwordHash);
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
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

    /**
     * Override of Object's equals method.
     * @param obj Other object to compare.
     * @return True if the objects are strictly equal, false otherwise.
     */
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
