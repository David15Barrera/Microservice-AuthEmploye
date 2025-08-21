package com.serviceAuth.authService.infrastructure.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.temporal.ChronoUnit;

@ConfigurationProperties("security.token")
public record TokenProperty(
        Long expirationTime,
        ChronoUnit timeUnit) {
}
