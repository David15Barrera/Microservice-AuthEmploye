package com.serviceAuth.authService.user.domain.model;

import com.serviceAuth.authService.common.application.exception.EntityConflictUserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class UserCustomerEntityDomain extends UserEntityDomain {

    private UUID customerId;

    public UserCustomerEntityDomain(UUID id, UUID customerId, String email, boolean active, Role role, String password) {
        super(id,email,password, active, role);
        this.customerId = customerId;
        validate();
    }

    public UserCustomerEntityDomain(UUID customerId, String email, boolean active, Role role, String password) {
        super(email,password, active, role);
        this.customerId = customerId;
        validate();
    }

    private void validate() {

        if (customerId == null) {
            throw new EntityConflictUserType("El usuario debe ser un empleado");
        }

        if (Objects.equals(this.role, "CUSTOMER")){
            throw new EntityConflictUserType("El usuario no pude ser cliente");
        }
    }


}
