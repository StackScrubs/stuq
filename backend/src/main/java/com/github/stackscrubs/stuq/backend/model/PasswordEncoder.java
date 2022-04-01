package com.github.stackscrubs.stuq.backend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoder extends Pbkdf2PasswordEncoder {
    private static final int SALT_LENGTH = 16;
    private static final int ITERS = 200_000;
    private static final int HASH_WIDTH = 512;

    private static PasswordEncoder instance;

    private PasswordEncoder() {
        super(getPasswordSecret(), SALT_LENGTH, ITERS, HASH_WIDTH);
    }

    public synchronized static PasswordEncoder getInstance() {
        if (instance == null) {
            instance = new PasswordEncoder();
        }
        return instance;
    }

    private static Properties getApplicationProperties() {
        Properties properties = new Properties();
        try (InputStream propertiesStream = PasswordEncoder.class.getResourceAsStream("/application.properties")) {
            properties.load(propertiesStream);
        } catch (IOException e) {
            throw new RuntimeException("unable to load properties: " + e);
        }
        return properties;
    }

    private static String getPasswordSecret() {
        String secret = getApplicationProperties().getProperty("password.secret");
        if (secret == null) {
            throw new IllegalStateException("password.secret must be specified");
        }
        return secret;
    }
}
