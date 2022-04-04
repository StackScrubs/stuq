package com.github.stackscrubs.stuq.backend.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class used when a subject is not found.
 * Resolves to a 404 NOT FOUND response.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "subject not found")
public class SubjectNotFoundException extends RuntimeException {
    
}
