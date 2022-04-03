package com.github.stackscrubs.stuq.backend.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.lang.NonNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSubjectRequest {
    
    @NonNull
    private final String code;

    @NonNull
    private final int termYear;

    @NonNull
    private final String termPeriod;

    @NonNull 
    private final String name;

    @JsonCreator
    public CreateSubjectRequest(
        @NonNull String code, 
        @NonNull int termYear, 
        @NonNull String termPeriod, 
        @NonNull String name
    ) {
        this.code = Objects.requireNonNull(code, "code cannot be null");
        this.termYear = Objects.requireNonNull(termYear, "termYear cannot be null");
        this.termPeriod = Objects.requireNonNull(termPeriod, "termPeriod cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
    }

    @JsonProperty
    public String getCode() {
        return code;
    }

    @JsonProperty
    public int getTermYear() {
        return termYear;
    }
    
    @JsonProperty
    public String getTermPeriod() {
        return termPeriod;
    }
    
    @JsonProperty
    public String getName() {
        return name;
    }
}
