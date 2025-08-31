package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import com.serviceAuth.authService.user.domain.model.Role;
import lombok.Builder;

import java.util.UUID;

@Builder(toBuilder = true)
public record UserEmployeeResponseDto(
         UUID id,
         UUID employeeId,
         String email,
         boolean active,
         Role role
) {
}
