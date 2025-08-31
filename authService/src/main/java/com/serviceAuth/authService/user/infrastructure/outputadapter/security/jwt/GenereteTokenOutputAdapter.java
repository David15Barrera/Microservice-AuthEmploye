package com.serviceAuth.authService.user.infrastructure.outputadapter.security.jwt;

import com.serviceAuth.authService.infrastructure.property.TokenProperty;
import com.serviceAuth.authService.user.application.ports.output.security.jwt.GeneratingToken;
import com.serviceAuth.authService.user.domain.model.UserCustomerEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenereteTokenOutputAdapter implements GeneratingToken {

    private final JwtEncoder jwtEncoder;
    private final TokenProperty tokenProperty;

    @Override
    public String generateToken(UserEntityDomain userEntityDomain) {
        Instant now = Instant.now();

        String employeeId = (userEntityDomain instanceof UserEmployeeEntityDomain)
                ? ((UserEmployeeEntityDomain) userEntityDomain).getEmployeeId().toString() : null;

        String customerId = (userEntityDomain instanceof UserCustomerEntityDomain)
                ? ((UserCustomerEntityDomain) userEntityDomain).getCustomerId().toString() : null;

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("auth-identity")
                .issuedAt(now)
                .expiresAt(now.plus(tokenProperty.expirationTime(), tokenProperty.timeUnit()))
                .subject(String.valueOf(userEntityDomain.getId()))
                .claim("auths", List.of("ROLE_".concat(userEntityDomain.getRole().getName())))
                .claim("employee_id", employeeId == null ? "null" : employeeId)
                .claim("customer_id", customerId == null ? "null" : customerId)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
}
