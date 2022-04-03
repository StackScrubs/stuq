package com.github.stackscrubs.stuq.backend.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

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
            // Allow all requests to /session
        http.authorizeRequests().antMatchers("/session").permitAll()
            // Require auth for all other urls.
            .anyRequest().authenticated().and()
            .exceptionHandling().authenticationEntryPoint(sessionAuthEntryPoint).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
;
        http.addFilterBefore(sessionAuthRequestFilter, UsernamePasswordAuthenticationFilter.class);
   }
}
