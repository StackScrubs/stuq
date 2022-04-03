package com.github.stackscrubs.stuq.backend.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    TEACHER("TEACHER"),
    TEACHING_ASSISTANT("TEACHING_ASSISTANT"),
    STUDENT("STUDENT");

    private final String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + this.name;
    }
}
