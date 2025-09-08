package com.serviceAuth.authService.employee.application.ports.input;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.UUID;

public interface FindingEmployeeByIdInputPort {
    EmployeeDomainEntity findById(UUID idd);
}
