package com.github.stackscrubs.stuq.backend.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

/**
 * Singleton class that performs hashing operations on passwords to make them irretrievable.
 */
public class PasswordEncoder extends Pbkdf2PasswordEncoder {
    private static final int SALT_LENGTH = 16;
    private static final int ITERS = 200_000;
    private static final int HASH_WIDTH = 512;

    private static PasswordEncoder instance;

    /**
     * Default constructor.
     * Private as this class uses the singleton design pattern.
     */
    private PasswordEncoder() {
        super(getPasswordSecret(), SALT_LENGTH, ITERS, HASH_WIDTH);
    }

    /**
     * Thread-safe singleton getter.
     * @return Instance of the singleton object.
     */
    public synchronized static PasswordEncoder getInstance() {
        if (instance == null) {
            instance = new PasswordEncoder();
        }
        return instance;
    }

    /**
     * Helper method for getting the properties resource.
     * @return Application properties resource as a Property object.
     */
    private static Properties getApplicationProperties() {
        Properties properties = new Properties();
        try (InputStream propertiesStream = PasswordEncoder.class.getResourceAsStream("/application.properties")) {
            properties.load(propertiesStream);
        } catch (IOException e) {
            throw new RuntimeException("unable to load properties: " + e);
        }
        return properties;
    }

    /**
     * Helper method for extracting a password secret from the properties resource.
     * @return Password secret found in application properties.
     */
    private static String getPasswordSecret() {
        String secret = getApplicationProperties().getProperty("password.secret");
        if (secret == null) {
            throw new IllegalStateException("password.secret must be specified");
        }
        return secret;
    }
}
