package com.github.stackscrubs.stuq.backend.model.jpa;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Date;
import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

@Entity
public class Session {
    public static final Duration ABSOLUTE_EXPIRY_DURATION = Duration.ofMinutes(30);
    public static final Duration IDLE_EXPIRY_DURATION = Duration.ofHours(4);
    public static final int TOKEN_BYTES = 32;

    @Id
    private final byte[] token;

    @Column(nullable = false)
    private final User user;

    @Column(nullable = false)
    private final Instant absoluteExpiry;

    @Column(nullable = false)
    private Instant idleExpiry;

    public Session(@NonNull User user) {
        Instant now = Instant.now();
        this.token = generateToken();
        this.user = Objects.requireNonNull(user, "user cannot be null");
        this.idleExpiry = now.plus(IDLE_EXPIRY_DURATION);
        this.absoluteExpiry = now.plus(ABSOLUTE_EXPIRY_DURATION);
    }

    private static byte[] generateToken() {
        byte[] token = new byte[TOKEN_BYTES];
        try {
            SecureRandom.getInstanceStrong().nextBytes(token);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return token;
    }

    public boolean isExpired() {
        Instant now = Instant.now();
        return (
            now.isAfter(this.idleExpiry) ||
            now.isAfter(this.absoluteExpiry)
        );
    }

    private void throwIfExpired() {
        if (isExpired()) throw new IllegalStateException("the session is expired");
    }

    public User getUser() {
        throwIfExpired();
        return this.user;
    }
}