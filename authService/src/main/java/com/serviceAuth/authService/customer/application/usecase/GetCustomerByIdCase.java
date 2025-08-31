package com.serviceAuth.authService.customer.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityNotFount;
import com.serviceAuth.authService.customer.application.ports.input.FindingCustomerByIdInputPort;
import com.serviceAuth.authService.customer.application.ports.output.FindingCustomerByIdOutputPort;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class GetCustomerByIdCase implements FindingCustomerByIdInputPort {
    private final FindingCustomerByIdOutputPort finding;
    @Override
    public CustomerDomainEntity findById(UUID id) {
        return finding.findById(id).orElseThrow(() -> new EntityNotFount("Customer not found"));
    }
}
