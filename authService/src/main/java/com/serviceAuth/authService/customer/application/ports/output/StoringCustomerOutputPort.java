package com.serviceAuth.authService.customer.application.ports.output;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

public interface StoringCustomerOutputPort {
    CustomerDomainEntity save(CustomerDomainEntity customer);
}
