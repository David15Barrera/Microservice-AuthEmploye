package com.serviceAuth.authService.user.application.ports.output.persistence;


import com.serviceAuth.authService.user.domain.model.UserEmployee;

import java.util.Optional;

public interface FindingUserEmployeeByEmailAndRoleOutputPort {
    Optional<UserEmployee> findByEmailAndRole(String email, String role);
}
