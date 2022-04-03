package com.github.stackscrubs.stuq.backend.controller;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.service.SessionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

/**
 * SessionController handles requests for the SessionService.
 */
@RestController
@RequestMapping(value = "/session")
@EnableAutoConfiguration
@CrossOrigin(origins = "") //TODO:
public class SessionController {
    @Autowired
    private SessionService service;

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Session create(@RequestBody UserCredentials credentials) {
        return this.service.create(credentials);
    }

    @DeleteMapping(value = "{token}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable byte[] token) {
        this.service.delete(token);
    }
}
