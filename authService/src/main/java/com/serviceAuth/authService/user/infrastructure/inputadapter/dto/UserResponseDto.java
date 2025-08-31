package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserResponseDto(
        String id,
        String email,
        boolean active,
        String roleName,
        String employeeId,
        String customerId
) {


}
