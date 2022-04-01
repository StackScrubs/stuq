package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNull;

@Entity
public class Subject {
    @Id
    @EmbeddedId
    @Column(nullable = false)
    private SubjectId id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teacher", joinColumns = {
        @JoinColumn(name = "teacher", nullable = false),
        @JoinColumn(name = "subject_code", nullable = false),
        @JoinColumn(name = "subject_term", nullable = false)
    })
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teaching_assistant", joinColumns = {
        @JoinColumn(name = "teaching_assistant", nullable = false),
        @JoinColumn(name = "subject_code", nullable = false),
        @JoinColumn(name = "subject_term", nullable = false)
    })
    private Set<TeachingAssistant> teachingAssistants = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Assignment> assignments;

    Subject() {}

    public Subject(@NonNull SubjectId id) {
        this.id = id;
    }

    public SubjectId getId() {
        return this.id;
    }

    public Set<Teacher> getTeachers() {
        return this.teachers;
    }

    public Set<TeachingAssistant> getTeachingAssistants() {
        return this.teachingAssistants;
    }

    public Set<Assignment> getAssignments() {
        return this.assignments;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.teachers, this.teachingAssistants, this.assignments);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subject other = (Subject) obj;
        if (this.assignments == null) {
            if (other.assignments != null)
                return false;
        } else if (!this.assignments.equals(other.assignments))
            return false;
        if (this.id == null) {
            if (other.id != null)
                return false;
        } else if (!this.id.equals(other.id))
            return false;
        if (this.teachers == null) {
            if (other.teachers != null)
                return false;
        } else if (!this.teachers.equals(other.teachers))
            return false;
        if (this.teachingAssistants == null) {
            if (other.teachingAssistants != null)
                return false;
        } else if (!this.teachingAssistants.equals(other.teachingAssistants))
            return false;
        return true;
    }
}
