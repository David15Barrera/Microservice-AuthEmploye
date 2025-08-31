package com.serviceAuth.authService.customer.application.ports.input;

import java.util.UUID;

public interface DeletingCustomerInputPort {
    void delete(UUID id);
}
