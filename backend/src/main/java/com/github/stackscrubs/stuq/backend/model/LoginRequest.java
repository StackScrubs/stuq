package com.github.stackscrubs.stuq.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequest {

    private final String email;
    private final String password;

    @JsonCreator
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
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
