package com.serviceAuth.authService.customer.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.customer.application.ports.input.CreatingCustomerInputPort;
import com.serviceAuth.authService.customer.application.ports.output.FindingCustomerByCuiOutputPort;
import com.serviceAuth.authService.customer.application.ports.output.FindingCustomerByEmailOutputPort;
import com.serviceAuth.authService.customer.application.ports.output.StoringCustomerOutputPort;
import com.serviceAuth.authService.customer.application.usecase.dto.CreateCustomerDto;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateCustomerCase implements CreatingCustomerInputPort {
    private final StoringCustomerOutputPort storing;
    private final FindingCustomerByEmailOutputPort findByEmail;
    private final FindingCustomerByCuiOutputPort findByCui;

    @Override
    public CustomerDomainEntity create(CreateCustomerDto dto) {
        findByEmail.findByEmail(dto.getEmail()).ifPresent(c -> { throw new EntityAlreadyExistsException("Email already in use"); });
        findByCui.findByCui(dto.getCui()).ifPresent(c -> { throw new EntityAlreadyExistsException("CUI already in use"); });
        return storing.save(dto.toDomain());
    }
}
