package com.serviceAuth.authService.customer.infrastructure.inputadapter.dto;

import lombok.Value;

import java.util.UUID;

@Value
public class CustomerResponseDto {
    UUID id;
    String fullName;
    String cui;
    String phone;
    String email;
    String address;
    Integer loyaltyPoints;
}
