package com.github.stackscrubs.stuq.backend.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class used when a queue that already exists is attempted to be re-created.
 * Resolves to a 409 CONFLICT response.
 */
@ResponseStatus(value = HttpStatus.CONFLICT, reason = "queue already exists")
public class QueueAlreadyExistsException extends RuntimeException {
    
}
