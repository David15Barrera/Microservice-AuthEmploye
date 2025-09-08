package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindingEmployeeByIdOutputPort {
    Optional<EmployeeDomainEntity> findById(UUID id);
}
