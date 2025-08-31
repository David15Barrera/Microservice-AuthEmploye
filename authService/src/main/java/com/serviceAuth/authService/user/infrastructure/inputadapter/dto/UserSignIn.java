package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import com.serviceAuth.authService.user.application.usecase.authentication.AuthUserDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class UserSignIn {

    @NotBlank String email;
    @NotBlank String password;

    public AuthUserDto toDomain(){
        return new AuthUserDto(email, password);
    }
}
