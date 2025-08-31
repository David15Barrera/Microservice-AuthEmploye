package com.serviceAuth.authService.user.application.usecase.authentication;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.user.application.ports.input.AuthenticatingUserInputPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.FindingUserByEmailOutputPort;
import com.serviceAuth.authService.user.application.ports.output.security.UnAuthenticated;
import com.serviceAuth.authService.user.application.ports.output.security.jwt.GeneratingToken;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@UseCase
@Validated
@RequiredArgsConstructor
public class AuthenticationUserCase implements AuthenticatingUserInputPort {

    private final UnAuthenticated unAuthenticated;
    private final AuthenticationManager authenticationManager;
    private final FindingUserByEmailOutputPort findingUserByEmailOutputPort;
    private final GeneratingToken generatingToken;


    @Override
    @Transactional
    public UserEntityDomain authenticationUser(AuthUserDto authUserDto) {
        authenticationManager.authenticate(unAuthenticated.unauthenticatedUser(authUserDto.getEmail(), authUserDto.getPassword()));

        return findingUserByEmailOutputPort.findByEmail(authUserDto.getEmail())
                .map(user -> { user.setToken(generatingToken.generateToken(user)); return user; })
                .orElseThrow(() -> new InsufficientAuthenticationException("No se encontro el registro del usuario"));
    }
}
