package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import com.serviceAuth.authService.user.application.usecase.confirmcode.ConfirmCodeUseDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class ConfirmUserDto{

    @NotBlank String email;
    @NotBlank String code;

    public ConfirmCodeUseDto toDomain(){
        return new ConfirmCodeUseDto(email, code);
    }
}
