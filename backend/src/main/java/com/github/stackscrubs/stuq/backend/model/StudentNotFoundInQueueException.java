package com.github.stackscrubs.stuq.backend.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class used when a student is not found in a queue.
 * Resolves to a 404 NOT FOUND response.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "student not found in queue")
public class StudentNotFoundInQueueException extends RuntimeException {
    
}
