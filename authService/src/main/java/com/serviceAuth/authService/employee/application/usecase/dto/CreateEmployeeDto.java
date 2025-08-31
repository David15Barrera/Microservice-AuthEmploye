package com.serviceAuth.authService.employee.application.usecase.dto;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
@AllArgsConstructor
public class CreateEmployeeDto {
    private String fullName;
    private String email;
    private String cui;
    private String phone;
    private String jobPosition;
    private String address;
    private BigDecimal salary;
    private UUID HotelId;
    private UUID RestaurantId;

    public EmployeeDomainEntity toDomain(){
        return new EmployeeDomainEntity(fullName, cui, phone, email, jobPosition, salary, address, HotelId, RestaurantId);
    }

}
