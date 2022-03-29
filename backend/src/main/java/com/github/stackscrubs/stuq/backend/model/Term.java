package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Term {
    @Id
    private int year;
    
    @Id
    private String period;
}
