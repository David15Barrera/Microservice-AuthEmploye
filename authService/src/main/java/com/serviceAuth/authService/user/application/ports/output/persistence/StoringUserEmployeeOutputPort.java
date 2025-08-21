package com.serviceAuth.authService.user.application.ports.output.persistence;


import com.serviceAuth.authService.employee.domain.model.Employee;
import com.serviceAuth.authService.user.domain.model.UserEmployee;

public interface StoringUserEmployeeOutputPort {
    UserEmployee save(UserEmployee userEmployee, Employee employee);
}
