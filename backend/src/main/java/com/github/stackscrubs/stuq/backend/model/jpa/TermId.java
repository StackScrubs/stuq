package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

/**
 * JPA specification of an embeddable term ID.
 */
@Embeddable
public class TermId implements Serializable {
    
    @Column(name = "`year`", nullable = false)
    private int year;
    
    @Column(nullable = false)
    private String period;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    TermId() {}

    /**
     * Constructor.
     * @param year The year in which this term takes place.
     * @param period The period in which this term takes place.
     */
    public TermId(@NonNull int year, @NonNull String period) {
        this.year = year;
        this.period = period;
    }

    /**
     * Getter for year.
     * @return The year in which this term takes place.
     */
    public int getYear() {
        return this.year;
    }

    /**
     * Getter for period.
     * @return The period in which this term takes place.
     */
    public String getPeriod() {
        return this.period;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.year, this.period);
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
        TermId other = (TermId) obj;
        if (period == null) {
            if (other.period != null)
                return false;
        } else if (!period.equals(other.period))
            return false;
        if (year != other.year)
            return false;
        return true;
    }
}
