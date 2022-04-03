package com.github.stackscrubs.stuq.backend.security;

import java.io.IOException;
import java.util.Base64;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;
import com.github.stackscrubs.stuq.backend.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SessionAuthRequestFilter extends OncePerRequestFilter {
    @Autowired
    private SessionService sessionService;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        Session session = getSessionFromRequest(request).orElse(null);
        if (session != null) {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                session, 
                null,
                Collections.emptyList()
            );
            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }
    
    private Optional<Session> getSessionFromRequest(HttpServletRequest request) {
        final String authPrefix = "Bearer ";

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(authPrefix)) {
            return Optional.empty();
        }
        String base64SessionToken = authorizationHeader.substring(authPrefix.length());
        byte[] sessionToken = Base64.getDecoder().decode(base64SessionToken);
        return sessionService.find(sessionToken);
    }
}
