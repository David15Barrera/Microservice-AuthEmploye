package com.serviceAuth.authService.user.application.usecase.authentication;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class AuthUserDto {
    String email;
    String password;
}
