package com.serviceAuth.authService.employee.application.ports.output;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.util.List;
import java.util.UUID;

public interface FindEmpleadoByRestaurantOutputPort {
    List<EmployeeDomainEntity> findByRestaurantId(UUID id);
}
