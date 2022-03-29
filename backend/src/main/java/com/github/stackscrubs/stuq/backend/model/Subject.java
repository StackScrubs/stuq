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

@Entity
public class Subject {
    @Id
    private String code;

    @Id
    private Term term;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teacher",
        joinColumns = { @JoinColumn(name = "teacher", nullable = false, updatable = false) },
        inverseJoinColumns = { @JoinColumn(name = "subject", nullable = false, updatable = false) }
    )
    Set<Teacher> teachers = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "subject_teaching_assistant",
        joinColumns = {@JoinColumn(name = "teaching_assistant", nullable = false, updatable = false)}, 
        inverseJoinColumns = {@JoinColumn(name = "subject", nullable = false, updatable = false)}
    )
    Set<TeachingAssistant> teachingAssistants = new HashSet<>();
}
