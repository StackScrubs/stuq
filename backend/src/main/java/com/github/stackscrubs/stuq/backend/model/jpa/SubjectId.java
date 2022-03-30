package com.github.stackscrubs.stuq.backend.model.jpa;

import java.io.Serializable;

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
        return code;
    }

    public Term getTerm() {
        return term;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((term == null) ? 0 : term.hashCode());
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
        SubjectId other = (SubjectId) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (term == null) {
            if (other.term != null)
                return false;
        } else if (!term.equals(other.term))
            return false;
        return true;
    }
}
