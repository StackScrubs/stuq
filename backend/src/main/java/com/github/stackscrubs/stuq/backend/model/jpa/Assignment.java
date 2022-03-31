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
        @JoinColumn(referencedColumnName = "term", nullable = false),
    })
    private Subject subject;

    @OneToMany(mappedBy = "id.assignment", fetch = FetchType.LAZY)
    private Set<Submission> submissions;

    public Assignment(@NonNull String name, @NonNull Subject subject) {
        this.name = name;
        this.subject = subject;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Subject getSubject() {
        return this.subject;
    }

    public Set<Submission> getSubmissions() {
        return this.submissions;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.subject, this.submissions);
    }

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
