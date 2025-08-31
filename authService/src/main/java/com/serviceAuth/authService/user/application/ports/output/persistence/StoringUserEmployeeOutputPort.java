package com.serviceAuth.authService.user.application.ports.output.persistence;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;

public interface StoringUserEmployeeOutputPort {
    UserEmployeeEntityDomain save(UserEmployeeEntityDomain userEmployee, EmployeeDomainEntity employee);
}
