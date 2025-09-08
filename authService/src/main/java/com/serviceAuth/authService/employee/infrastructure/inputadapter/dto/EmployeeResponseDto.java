package com.serviceAuth.authService.employee.infrastructure.inputadapter.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder(toBuilder = true)
public record EmployeeResponseDto(
        UUID id,
        String email,
        String fullName,
        String cui,
        String phone,
        String jobPosition,
        BigDecimal salary,
        String address,
        UUID hotelId,
        UUID restaurantId
) {
}
