package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.Optional;

public interface FindingEmployeeByEmailOutputPort {
    Optional<EmployeeDomainEntity> findByEmployeeByEmail(String email);
}
