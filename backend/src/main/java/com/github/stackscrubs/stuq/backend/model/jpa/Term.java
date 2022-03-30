package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Term {
    @Id
    @NonNull
    private int year;
    
    @Id
    @NonNull
    private String period;

    public Term(@NonNull int year, @NonNull String period) {
        this.year = year;
        this.period = period;
    }
}
