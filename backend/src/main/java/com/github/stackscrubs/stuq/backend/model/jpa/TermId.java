package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

@Embeddable
public class TermId {
    @Column(nullable = false)
    private int year;
    
    @Column(nullable = false)
    private String period;

    public TermId(@NonNull int year, @NonNull String period) {
        this.year = year;
        this.period = period;
    }

    public int getYear() {
        return this.year;
    }

    public String getPeriod() {
        return this.period;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.year, this.period);
    }

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
