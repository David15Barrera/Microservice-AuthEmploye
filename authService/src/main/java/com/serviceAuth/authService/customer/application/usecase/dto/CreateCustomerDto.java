package com.serviceAuth.authService.customer.application.usecase.dto;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CreateCustomerDto {
    String fullName;
    String cui;
    String phone;
    String email;
    String address;
    Integer loyaltyPoints;

    public CustomerDomainEntity toDomain() {
        return new CustomerDomainEntity(null, fullName, cui, phone, email, address, loyaltyPoints == null ? 0 : loyaltyPoints);
    }
}
