package com.github.stackscrubs.stuq.backend.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Handles exception that occurr while authenticating the user.
 */
@Component
public class SessionAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    Logger logger = LoggerFactory.getLogger(SessionAuthExceptionEntryPoint.class);

    @Override
    public void commence(
        HttpServletRequest request, 
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException, ServletException {
        logger.debug("rejecting unauthorized request");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }
}
