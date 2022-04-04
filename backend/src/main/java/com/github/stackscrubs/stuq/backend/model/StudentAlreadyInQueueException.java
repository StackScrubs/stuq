package com.github.stackscrubs.stuq.backend.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class used when a student who is already in a queue is attempted to be added again.
 * Resolves to a 409 CONFLICT response.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "student is already in queue")
public class StudentAlreadyInQueueException extends RuntimeException {
    
}
