package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a term.
 */
@Entity
public class Term {

    @EmbeddedId
    @Column(nullable = false)
    private TermId id;

    @OneToMany(mappedBy = "id.term", fetch = FetchType.LAZY)
    private Set<Subject> subjects;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    Term() {}

    /**
     * Constructor.
     * @param id The ID of the term.
     */
    public Term(@NonNull TermId id) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
    }

    /**
     * Getter for ID.
     * @return The term's ID.
     */
    public TermId getId() {
        return this.id;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id);
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
        Term other = (Term) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.subjects == null) {
            if (other.subjects != null)
                return false;
        } else if (!this.subjects.equals(other.subjects))
            return false;
        return true;
    }
}
