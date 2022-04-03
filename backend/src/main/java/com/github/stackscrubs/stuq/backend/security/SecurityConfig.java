package com.github.stackscrubs.stuq.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SessionAuthEntryPoint sessionAuthEntryPoint;

    @Autowired
    private SessionAuthRequestFilter sessionAuthRequestFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Disable CSRF, as it is not needed for STUQ
        http.csrf().disable()
        // Allow all requests to /session
            .authorizeRequests().antMatchers("/session/**").permitAll()
        // Require auth for all other urls.
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(sessionAuthEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);

        http.addFilterBefore(sessionAuthRequestFilter, UsernamePasswordAuthenticationFilter.class);
   }
}
