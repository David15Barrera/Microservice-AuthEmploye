package com.serviceAuth.authService.customer.application.ports.output;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.Optional;
import java.util.UUID;

public interface FindingCustomerByIdOutputPort {
    Optional<CustomerDomainEntity> findById(UUID id);
}
