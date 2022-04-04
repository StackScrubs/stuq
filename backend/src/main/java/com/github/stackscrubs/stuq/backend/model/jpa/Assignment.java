package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of an assignment.
 */
@Entity
public class Assignment {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(referencedColumnName = "code", nullable = false),
        @JoinColumn(referencedColumnName = "term_year", nullable = false),
        @JoinColumn(referencedColumnName = "term_period", nullable = false)
    })
    private Subject subject;

    @OneToMany(mappedBy = "id.assignment", fetch = FetchType.LAZY)
    private Set<Submission> submissions;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    Assignment() {}

    /**
     * Constructor.
     * @param name Name of the assignment.
     * @param subject The subject the assignment was handed out in.
     */
    public Assignment(@NonNull String name, @NonNull Subject subject) {
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.subject = Objects.requireNonNull(subject, "subject cannot be null");
    }

    /**
     * Getter for ID.
     * @return Assignment ID.
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter for name.
     * @return Assignment name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for subject.
     * @return Subject that the assignment was handed out in.
     */
    public Subject getSubject() {
        return this.subject;
    }

    /**
     * Getter for all submissions associated with this assignment.
     * @return Set of all student submissions in this assignment.
     */
    public Set<Submission> getSubmissions() {
        return this.submissions;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.subject, this.submissions);
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
        Assignment other = (Assignment) obj;
        if (this.id != other.id)
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
            return false;
        if (this.subject == null) {
            if (other.subject != null)
                return false;
        } else if (!this.subject.equals(other.subject))
            return false;
        if (this.submissions == null) {
            if (other.submissions != null)
                return false;
        } else if (!this.submissions.equals(other.submissions))
            return false;
        return true;
    }
}
