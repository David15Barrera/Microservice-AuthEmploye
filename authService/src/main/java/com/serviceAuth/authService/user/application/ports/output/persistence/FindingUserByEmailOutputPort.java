package com.serviceAuth.authService.user.application.ports.output.persistence;

import com.serviceAuth.authService.user.domain.model.UserEntityDomain;

import java.util.Optional;

public interface FindingUserByEmailOutputPort {
    Optional<UserEntityDomain> findByEmail(String email);
}
