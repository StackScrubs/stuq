package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.lang.NonNull;

@Embeddable
public class SubjectId implements Serializable {
    @Column(nullable = false)
    private String code;
    
    @Column(nullable = false)
    private Term term;

    public SubjectId(@NonNull String code, @NonNull Term term) {
        this.code = code;
        this.term = term;
    }

    public String getCode() {
        return this.code;
    }

    public Term getTerm() {
        return this.term;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.term);
    }

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
