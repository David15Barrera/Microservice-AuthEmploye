package com.serviceAuth.authService.user.infrastructure.inputadapter.dto;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Builder;

@Builder(toBuilder = true)
public record TokenDto(
        String token,
        @JsonUnwrapped UserResponseDto user) {
}
