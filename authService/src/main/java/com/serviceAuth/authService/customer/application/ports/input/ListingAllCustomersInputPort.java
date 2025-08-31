package com.serviceAuth.authService.customer.application.ports.input;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;

import java.util.List;

public interface ListingAllCustomersInputPort {
    List<CustomerDomainEntity> list();
}
