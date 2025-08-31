package com.serviceAuth.authService.user.application.ports.output.security.encript;

public interface PasswordEncoderPort {
    String encode(String rawPassword);
}