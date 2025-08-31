package com.serviceAuth.authService.customer.infrastructure.inputadapter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Value;

@Value
public class CreateCustomerRequestDto {
    @NotBlank String fullName;
    @NotBlank String cui;
    String phone;
    @Email @NotBlank String email;
    @NotBlank String address;
    Integer loyaltyPoints;
}
