package com.github.stackscrubs.stuq.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentials {

    @NonNull
    private final String email;

    @NonNull 
    private final String password;

    @JsonCreator
    public UserCredentials(
        @NonNull @JsonProperty("email") String email, 
        @NonNull @JsonProperty("password") String password
    ) {
        this.email = Objects.requireNonNull(email, "email cannot be null");
        this.password = Objects.requireNonNull(password, "password cannot be null");
    }

    @JsonProperty("email")
    public String getEmail() {
        return this.email;
    }

    @JsonProperty("password")
    public String getPassword() {
        return this.password;
    }
}
