package com.github.stackscrubs.stuq.backend.controller;

import com.github.stackscrubs.stuq.backend.model.LoginResponse;
import com.github.stackscrubs.stuq.backend.model.LoginRequest;
import com.github.stackscrubs.stuq.backend.service.LoginService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping(value = "/login")
@EnableAutoConfiguration
@CrossOrigin(origins = "WRITE HOST HERE") //TODO:
public class LoginController {

    @Autowired
    private LoginService service;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @GetMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public LoginResponse login(@RequestBody LoginRequest login) {
        logger.info("Recieved login..." + login.getMail());
        LoginResponse response = service.login(login);
        logger.info("Sending result..." + response.getLoginStatus());
        return response;
    }
}
