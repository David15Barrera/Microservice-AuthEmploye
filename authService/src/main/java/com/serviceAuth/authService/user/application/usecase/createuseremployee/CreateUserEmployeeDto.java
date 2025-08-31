package com.serviceAuth.authService.user.application.usecase.createuseremployee;

import com.serviceAuth.authService.user.domain.model.Role;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class CreateUserEmployeeDto {
    private String email;
    private boolean active;
    private String password;
    private String cui;

    public UserEmployeeEntityDomain toDomain(UUID employeeId, Role role) {
        return new UserEmployeeEntityDomain(employeeId,email, active, role,password);
    }
}
