package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.Optional;

public interface FindingEmployeeByCuiOutputPort {
    Optional<EmployeeDomainEntity> findByEmployeeByCui(String cui);
}
