package com.serviceAuth.authService.customer.application.ports.input;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.UUID;

public interface FindingCustomerByIdInputPort {
    CustomerDomainEntity findById(UUID id);
}
