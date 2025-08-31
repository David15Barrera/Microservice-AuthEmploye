package com.serviceAuth.authService.user.application.usecase.confirmcode;

import com.serviceAuth.authService.user.domain.model.UserConfirm;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ConfirmCodeUseDto {

    private String email;
    private String code;

    public UserConfirm toDomain(){
        return new UserConfirm(email, code);
    }
}
