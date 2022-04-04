package com.github.stackscrubs.stuq.backend;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring configuration class.
 * Enables scheduling of tasks,
 * used to schedule purging of expired sessions.
 */
@Configuration
@EnableScheduling
public class ScheduleConfig {
    
}
