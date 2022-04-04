package com.github.stackscrubs.stuq.backend.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class used when a subject already exists.
 * Resolves to a 409 CONFLICT response.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "subject already exists")
public class SubjectAlreadyExistsException extends RuntimeException {
    
}
