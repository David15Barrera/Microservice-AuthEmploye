package com.serviceAuth.authService.user.domain.model;

import com.serviceAuth.authService.common.application.exception.EntityConflictUserType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class UserEmployee {
    private UUID id;
    private UUID employeeId;
    private String email;
    private String password;
    private boolean active;
    private Role role;

    public UserEmployee(UUID id, UUID employeeId, String email, boolean active, Role role, String password) {
        this.id = id;
        this.employeeId = employeeId;
        this.email = email;
        this.active = active;
        this.role = role;
        this.password = password;
        validate();
    }

    public UserEmployee(UUID employeeId, String email, boolean active, Role role,  String password) {
        this.employeeId = employeeId;
        this.email = email;
        this.active = active;
        this.role = role;
        this.password = password;
        validate();
    }

    private void validate() {
        Objects.requireNonNull(email, "Email no puede ser nulo");
        Objects.requireNonNull(role, "Role no puede ser nulo");

        if (employeeId == null) {
            throw new EntityConflictUserType("El usuario debe ser un empleado");
        }

        if (Objects.equals(role.name, "CUSTOMER")){
            throw new EntityConflictUserType("El usuario no pude ser cliente");
        }
    }


}
