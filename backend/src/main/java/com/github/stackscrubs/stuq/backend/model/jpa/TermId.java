package com.github.stackscrubs.stuq.backend.model.jpa;

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
        return year;
    }

    public String getPeriod() {
        return period;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((period == null) ? 0 : period.hashCode());
        result = prime * result + year;
        return result;
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
