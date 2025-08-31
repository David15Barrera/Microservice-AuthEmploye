package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import com.serviceAuth.authService.user.application.usecase.createuseremployee.CreateUserEmployeeDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserEmployeeRequest{

    @NotBlank
    private final String email;
    @NotBlank
    private  final String password;
    @NotBlank
    private  final String cui;

    public CreateUserEmployeeDto toDomain(){
        return new CreateUserEmployeeDto(email, true, password, cui);
    }

}
