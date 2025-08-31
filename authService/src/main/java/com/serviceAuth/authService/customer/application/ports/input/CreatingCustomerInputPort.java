package com.serviceAuth.authService.customer.application.ports.input;

import com.serviceAuth.authService.customer.application.usecase.dto.CreateCustomerDto;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import jakarta.validation.Valid;

public interface CreatingCustomerInputPort {
    CustomerDomainEntity create(@Valid CreateCustomerDto dto);
}
