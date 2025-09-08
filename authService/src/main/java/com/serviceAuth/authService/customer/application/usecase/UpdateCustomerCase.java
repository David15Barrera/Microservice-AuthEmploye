package com.serviceAuth.authService.customer.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.common.application.exception.EntityNotFount;
import com.serviceAuth.authService.customer.application.ports.input.UpdatingCustomerInputPort;
import com.serviceAuth.authService.customer.application.ports.output.*;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class UpdateCustomerCase implements UpdatingCustomerInputPort {
    private final StoringCustomerOutputPort storing;
    private final FindingCustomerByIdOutputPort findById;
    private final FindingCustomerByEmailOutputPort findByEmail;
    private final FindingCustomerByCuiOutputPort findByCui;

    @Override
    public CustomerDomainEntity update(UUID id,
                                       String fullName,
                                       String cui,
                                       String phone,
                                       String email,
                                       String address,
                                       Integer loyaltyPoints) {

        CustomerDomainEntity current = findById.findById(id).orElseThrow(() -> new EntityNotFount("Customer not found"));
        if (!current.getEmail().equalsIgnoreCase(email)) {
            findByEmail.findByEmail(email).ifPresent(c -> {
                if (!c.getId().equals(id)) { // 👈 ignorar el mismo cliente
                    throw new EntityAlreadyExistsException("Email already in use");
                }
            });
        }

        if (!current.getCui().equalsIgnoreCase(cui)) {
            findByCui.findByCui(cui).ifPresent(c -> {
                if (!c.getId().equals(id)) { // 👈 ignorar el mismo cliente
                    throw new EntityAlreadyExistsException("CUI already in use");
                }
            });
        }

        CustomerDomainEntity merged = new CustomerDomainEntity(
                id,
                fullName,
                cui,
                phone,
                email,
                address,
                loyaltyPoints == null ? 0 : loyaltyPoints
        );
        return storing.save(merged);
    }
}
