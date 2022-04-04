package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import org.springframework.lang.NonNull;

/**
 * JPA specification of an embeddable subject ID.
 */
@Embeddable
public class SubjectId implements Serializable {
    @Column(nullable = false)
    private String code;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "term_year", referencedColumnName = "year", nullable = false),
        @JoinColumn(name = "term_period", referencedColumnName = "period", nullable = false)
    })
    private Term term;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    SubjectId() {}

    /**
     * Constructor.
     * @param code The subject's code.
     * @param term The term in which this subject takes place in.
     */
    public SubjectId(@NonNull String code, @NonNull Term term) {
        this.code = Objects.requireNonNull(code, "code cannot be null");
        this.term = Objects.requireNonNull(term, "term cannot be null");
    }

    /**
     * Getter for code.
     * @return The subject's code.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Getter for term.
     * @return The term in which the subject takes place.
     */
    public Term getTerm() {
        return this.term;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.term);
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
        SubjectId other = (SubjectId) obj;
        if (this.code == null) {
            if (other.code != null)
                return false;
        } else if (!this.code.equals(other.code))
            return false;
        if (this.term == null) {
            if (other.term != null)
                return false;
        } else if (!this.term.equals(other.term))
            return false;
        return true;
    }
}
