package com.serviceAuth.authService.employee.application.ports.input;

import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import jakarta.validation.Valid;

public interface CreatingEmployeeInputPort {
    EmployeeDomainEntity createEmployee(@Valid CreateEmployeeDto createEmployeeDto);
}
