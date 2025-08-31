package com.serviceAuth.authService.customer.application.ports.input;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.UUID;

public interface UpdatingCustomerInputPort {
    CustomerDomainEntity update(UUID id, String fullName, String cui, String phone, String email, String address, Integer loyaltyPoints);
}
