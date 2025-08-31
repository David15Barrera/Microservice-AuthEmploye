package com.serviceAuth.authService.customer.application.ports.output;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.List;

public interface FindingAllCustomersOutputPort {
    List<CustomerDomainEntity> findAll();
}
