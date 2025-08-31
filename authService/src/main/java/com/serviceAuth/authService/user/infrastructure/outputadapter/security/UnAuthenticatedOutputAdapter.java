package com.serviceAuth.authService.user.infrastructure.outputadapter.security;

import com.serviceAuth.authService.user.application.ports.output.security.UnAuthenticated;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;

@Component
@RequiredArgsConstructor
public class UnAuthenticatedOutputAdapter implements UnAuthenticated {

    @Override
    public Authentication unauthenticatedUser(String email, String password) {
        return unauthenticated(email, password);
    }
}
