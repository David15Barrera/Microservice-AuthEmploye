package com.serviceAuth.authService.user.application.ports.output.persistence;

import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;

import java.util.Optional;

public interface FindingUserEmployeeByEmailAndRoleOutputPort {
    Optional<UserEmployeeEntityDomain> findByEmailAndRole(String email, String role);
}
