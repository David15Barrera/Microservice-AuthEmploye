package com.serviceAuth.authService.employee.application.ports.input;

import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.Employee;
import jakarta.validation.Valid;

public interface CreatingEmployeeInputPort {
    Employee createEmployee(@Valid CreateEmployeeDto createEmployeeDto);
}
