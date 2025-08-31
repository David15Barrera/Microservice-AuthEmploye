package com.serviceAuth.authService.user.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class UserEntityDomain {

    protected UUID id;
    protected String email;
    protected String password;
    protected boolean active;
    protected Role role;
    private String token;

    public UserEntityDomain(UUID id, String email, String password, boolean active, Role role) {
        this.id = id;
        this.active = active;
        this.password = password;
        this.email = email;
        this.role = role;
        this.validate();
    }

    public UserEntityDomain(String email, String password, boolean active, Role role) {
        this.active = active;
        this.password = password;
        this.email = email;
        this.role = role;
        this.validate();
    }

    private void validate() {
        Objects.requireNonNull(email, "Email no puede ser nulo");
        Objects.requireNonNull(role, "Role no puede ser nulo");
    }
}
