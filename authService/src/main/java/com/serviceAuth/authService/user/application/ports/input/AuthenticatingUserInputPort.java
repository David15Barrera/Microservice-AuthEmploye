package com.serviceAuth.authService.user.application.ports.input;

import com.serviceAuth.authService.user.application.usecase.authentication.AuthUserDto;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;

public interface AuthenticatingUserInputPort {
    UserEntityDomain authenticationUser(AuthUserDto authUserDto);
}
