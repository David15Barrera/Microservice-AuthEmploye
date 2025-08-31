package com.serviceAuth.authService.employee.application.ports.input;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.List;

public interface ListingAllEmployeesNoManagersInputPort {

    List<EmployeeDomainEntity> listAllEmployeesNoManagers();
}
