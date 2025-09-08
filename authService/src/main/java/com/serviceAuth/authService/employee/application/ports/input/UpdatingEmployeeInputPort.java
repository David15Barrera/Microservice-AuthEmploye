package com.serviceAuth.authService.employee.application.ports.input;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;

import java.math.BigDecimal;
import java.util.UUID;

public interface UpdatingEmployeeInputPort {
    EmployeeDomainEntity update(UUID id,
                                String Fullname,
                                String cui,
                                String phone,
                                String email,
                                String jobPosition,
                                BigDecimal salary,
                                String address,
                                UUID hotelId,
                                UUID restaurantId);
}
