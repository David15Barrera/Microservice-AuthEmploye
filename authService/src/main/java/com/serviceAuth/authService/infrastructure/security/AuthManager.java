package com.serviceAuth.authService.infrastructure.security;

import com.serviceAuth.authService.common.infrastructure.exception.AccountNotVerifiedException;
import com.serviceAuth.authService.common.infrastructure.exception.FailedAuthenticateException;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.authenticated;

@RequiredArgsConstructor
public class AuthManager implements AuthenticationManager {

    private final UserDBRepository userDBRepository;
    private final PasswordEncoder encoder;
    private final ConcurrentMap<String, String> signUpConfirmationCodes;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authUser) throws AuthenticationException {
        String email = authUser.getPrincipal().toString();
        String password = authUser.getCredentials().toString();

        if (signUpConfirmationCodes.containsKey(email)) {
            throw new AccountNotVerifiedException("La cuenta aun no se ha confirmado");
        }

        UserDBEntity user = userDBRepository.findByEmail(email, UserDBEntity.class)
                .filter(dbUser -> encoder.matches(password, dbUser.getPassword()))
                .orElseThrow(() -> new FailedAuthenticateException("El email o la contraseña es incorrecta"));

        String role = "ROLE_".concat(user.getRole().getName());

        return authenticated(email, password, List.of(new SimpleGrantedAuthority(role)));
    }
}