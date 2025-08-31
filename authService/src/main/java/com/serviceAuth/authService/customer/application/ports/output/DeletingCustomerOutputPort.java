package com.serviceAuth.authService.customer.application.ports.output;

import java.util.UUID;

public interface DeletingCustomerOutputPort {
    void deleteById(UUID id);
}
