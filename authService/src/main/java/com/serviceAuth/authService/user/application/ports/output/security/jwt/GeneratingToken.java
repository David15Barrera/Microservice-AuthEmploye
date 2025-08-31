package com.serviceAuth.authService.user.application.ports.output.security.jwt;

import com.serviceAuth.authService.user.domain.model.UserEntityDomain;

public interface GeneratingToken {

    String generateToken(UserEntityDomain userEntityDomain);
}
