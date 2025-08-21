package com.serviceAuth.authService.employee.application.ports.output;


import com.serviceAuth.authService.employee.domain.model.Employee;

public interface StoringEmployeeOutputPort {
    Employee save(Employee employee);
}
