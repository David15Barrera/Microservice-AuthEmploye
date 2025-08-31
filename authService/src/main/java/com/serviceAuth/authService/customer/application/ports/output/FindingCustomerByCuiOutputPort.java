package com.serviceAuth.authService.customer.application.ports.output;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.Optional;

public interface FindingCustomerByCuiOutputPort {
    Optional<CustomerDomainEntity> findByCui(String cui);
}
