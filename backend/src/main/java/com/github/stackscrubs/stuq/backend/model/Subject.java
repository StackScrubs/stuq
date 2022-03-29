package com.github.stackscrubs.stuq.backend.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subject {
    @Id
    private String code;

    @Id
    private String name;
}
