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

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.lang.NonNull;

/**
 * JPA Entity specification of a subject.
 */
@Entity
public class Subject {
    @Id
    @EmbeddedId
    @Column(nullable = false)
    private SubjectId id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teacher", joinColumns = {
        @JoinColumn(name = "teacher", nullable = false),
        @JoinColumn(name = "subject_code", nullable = false),
        @JoinColumn(name = "subject_term", nullable = false)
    })
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teaching_assistant", joinColumns = {
        @JoinColumn(name = "teaching_assistant", nullable = false),
        @JoinColumn(name = "subject_code", nullable = false),
        @JoinColumn(name = "subject_term", nullable = false)
    })
    @JsonIgnore
    private Set<TeachingAssistant> teachingAssistants = new HashSet<>();
    
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Assignment> assignments;

    /**
     * Default constructor.
     * Package-private as it is - and should only be used by JPA. 
     */
    Subject() {}

    /**
     * Constructor.
     * @param id The subject's ID.
     * @param name The full name of the subject.
     */
    public Subject(@NonNull SubjectId id, @NonNull String name) {
        this.id = Objects.requireNonNull(id, "id cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
    }

    /**
     * Getter for ID.
     * @return The subject's ID.
     */
    public SubjectId getId() {
        return this.id;
    }

    /**
     * Getter for name.
     * @return The subject's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for teachers who teach the subject.
     * @return Set of teachers who teach the subject.
     */
    public Set<Teacher> getTeachers() {
        return this.teachers;
    }

    /**
     * Getter for teaching assistants who assist the teacher in the subject.
     * @return Set of teaching assistants who assist the teacher in the subject.
     */
    public Set<TeachingAssistant> getTeachingAssistants() {
        return this.teachingAssistants;
    }

    /**
     * Getter for assignments handed out in the assignment.
     * @return Set of assignments handed out in the subject.
     */
    public Set<Assignment> getAssignments() {
        return this.assignments;
    }

    /**
     * Override of Object's hashCode method.
     * @return This object's hashcode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.teachers, this.teachingAssistants, this.assignments, this.name);
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
        if (this.name == null) {
            if (other.name != null)
                return false;
        } else if (!this.name.equals(other.name))
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
