package com.github.stackscrubs.stuq.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring configuration class.
 * Used to enforce the use of tokens when accessing
 * endpoints and otherwise configure the authentication
 * protocols to use.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final static String[] SWAGGER_WHITELIST = {
        "/swagger-resources/**",
        "/swagger-ui/**",
        "/v2/api-docs",
        "/webjars/**"
    };

    @Autowired
    private SessionAuthExceptionEntryPoint sessionAuthExceptionEntryPoint;

    @Autowired
    private SessionAuthRequestFilter sessionAuthRequestFilter;

    /**
     * Override of WebSecurityConfigurerAdapter's configure method.
     * Used to configure the authentication protocols to use.
     * Also specifies which endpoints do and don't require authentication.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF, as it is not needed for STUQ
        http.csrf().disable().authorizeRequests()
        // Allow all requests to /session and swagger-related resources 
            .antMatchers("/session/**").permitAll()
            .antMatchers(SWAGGER_WHITELIST).permitAll()
        // Require auth for all other urls.
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(sessionAuthExceptionEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.addFilterBefore(sessionAuthRequestFilter, UsernamePasswordAuthenticationFilter.class);
   }
}
