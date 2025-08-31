package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

public interface StoringEmployeeOutputPort {
    EmployeeDomainEntity save(EmployeeDomainEntity employee);
}
