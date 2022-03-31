package com.github.stackscrubs.stuq.backend.controller;

import com.github.stackscrubs.stuq.backend.model.jpa.Session;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.UserNotFoundException;
import com.github.stackscrubs.stuq.backend.service.SessionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/session")
@EnableAutoConfiguration
@CrossOrigin(origins = "") //TODO:
public class SessionController {
    @Autowired
    private SessionService service;

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Session> login(@RequestBody UserCredentials credentials) {
        try {
            Session session = this.service.create(credentials);
            return new ResponseEntity<>(session, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
