package com.serviceAuth.authService.user.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserConfirm {
    private String email;
    private String code;
}
