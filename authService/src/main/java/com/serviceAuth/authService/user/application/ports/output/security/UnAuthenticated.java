package com.serviceAuth.authService.user.application.ports.output.security;
import org.springframework.security.core.Authentication;

public interface UnAuthenticated {
    Authentication unauthenticatedUser(String email, String password);
}
