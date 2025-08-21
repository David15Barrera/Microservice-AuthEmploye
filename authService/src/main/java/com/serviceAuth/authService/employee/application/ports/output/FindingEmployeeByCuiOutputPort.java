package com.serviceAuth.authService.employee.application.ports.output;


import com.serviceAuth.authService.employee.domain.model.Employee;

import java.util.Optional;

public interface FindingEmployeeByCuiOutputPort {
    Optional<Employee> findByEmployeeByCui(String cui);
}
