package com.github.stackscrubs.stuq.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class.
 * Configured as a Spring Boot application, used to boot the backend.
 */
@SpringBootApplication
public class StuqBackendApplication {

	/**
	 * Main method responsible for running the backend.
	 * @param args Command-line arguments to be used by Spring Boot.
	 */
	public static void main(String[] args) {
		SpringApplication.run(StuqBackendApplication.class, args);
	}

}
