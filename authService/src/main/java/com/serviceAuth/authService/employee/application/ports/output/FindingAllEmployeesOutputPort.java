package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.List;

public interface FindingAllEmployeesOutputPort {

    List<EmployeeDomainEntity> findAllEmployees();

}
