package com.github.stackscrubs.stuq.backend.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.lang.NonNull;

@Entity
public class Subject {
    @Id
    @NonNull
    private String code;

    @Id
    @NonNull
    private Term term;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teacher",
        joinColumns = { @JoinColumn(name = "teacher", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "subject", nullable = false, updatable = false) }
    )
    private Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teaching_assistant",
        joinColumns = {@JoinColumn(name = "teaching_assistant", nullable = false, updatable = false)}, 
        inverseJoinColumns = {@JoinColumn(name = "subject", nullable = false, updatable = false)}
    )
    private Set<TeachingAssistant> teachingAssistants = new HashSet<>();

    public Subject(@NonNull String code, @NonNull Term term) {
        this.code = code;
        this.term = term;
    }

    public String getCode() {
        return this.code;
    }

    public Term getTerm() {
        return this.term;
    }

    public Set<Teacher> getTeachers() {
        return this.teachers;
    }

    public Set<TeachingAssistant> getTeachingAssistants() {
        return this.teachingAssistants;
    }
}
