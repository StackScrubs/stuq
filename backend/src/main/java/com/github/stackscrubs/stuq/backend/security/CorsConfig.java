package com.github.stackscrubs.stuq.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Spring configuration class.
 * Enables global CORS config. Used to allow CORS domain to
 * be specified as an environment variable.
 */
@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    
    /**
     * Override of WebMvcConfigurer's addCorsMappings methods.
     * Maps the application's CORS domain to endpoints.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins(System.getenv("CORS"));
    }
}
