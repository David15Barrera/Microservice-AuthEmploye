package com.serviceAuth.authService.customer.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.customer.application.ports.input.DeletingCustomerInputPort;
import com.serviceAuth.authService.customer.application.ports.output.DeletingCustomerOutputPort;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class DeleteCustomerCase implements DeletingCustomerInputPort {
    private final DeletingCustomerOutputPort deleting;
    @Override
    public void delete(UUID id) {
        deleting.deleteById(id);
    }
}
