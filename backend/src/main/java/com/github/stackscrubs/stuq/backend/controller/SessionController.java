package com.github.stackscrubs.stuq.backend.controller;

import com.github.stackscrubs.stuq.backend.model.LoginResponse;
import com.github.stackscrubs.stuq.backend.model.jpa.User;
import com.github.stackscrubs.stuq.backend.repository.SessionRepository;
import com.github.stackscrubs.stuq.backend.repository.UserRepository;

import java.util.Optional;

import com.github.stackscrubs.stuq.backend.model.UserCredentials;
import com.github.stackscrubs.stuq.backend.model.CreateSessionResponse;
import com.github.stackscrubs.stuq.backend.model.LoginRequest;
import com.github.stackscrubs.stuq.backend.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
    private LoginService service;

    Logger logger = LoggerFactory.getLogger(SessionController.class);

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<CreateSessionResponse> login(@RequestBody UserCredentials login) {
        logger.debug("Recieved login request..." + login.getEmail());        
        

        LoginResponse response = this.service.login(login);
        logger.info("Sending result..." + response.getLoginStatus());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
