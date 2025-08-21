package com.serviceAuth.authService.user.application.usecase.createuseremployee;

import com.serviceAuth.authService.user.domain.model.Role;
import com.serviceAuth.authService.user.domain.model.UserEmployee;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.UUID;

@Value
@AllArgsConstructor
public class CreateUserEmployeeDto {
    private String email;
    private boolean active;
    private String role;
    private String password;

    public UserEmployee toDomain(UUID employeeId){
        return new UserEmployee(employeeId,email, active, new Role(role),password);
    }
}
