package com.github.stackscrubs.stuq.backend.model.jpa;

import java.util.HashSet;
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
    @JoinTable(name = "subject_teacher",
        joinColumns = { @JoinColumn(name = "teacher", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "subject", nullable = false, updatable = false) })
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teaching_assistant",
        joinColumns = {@JoinColumn(name = "teaching_assistant", nullable = false, updatable = false)}, 
        inverseJoinColumns = {@JoinColumn(name = "subject", nullable = false, updatable = false)})
    private Set<TeachingAssistant> teachingAssistants = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Assignment> assignments;

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
}
