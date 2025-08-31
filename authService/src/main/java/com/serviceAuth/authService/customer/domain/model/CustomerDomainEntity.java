package com.serviceAuth.authService.customer.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class CustomerDomainEntity {
    private final UUID id;
    private final String fullName;
    private final String cui;
    private final String phone;
    private final String email;
    private final String address;
    private final Integer loyaltyPoints;

    public CustomerDomainEntity withId(UUID id) {
        return new CustomerDomainEntity(id, fullName, cui, phone, email, address, loyaltyPoints);
    }


}
