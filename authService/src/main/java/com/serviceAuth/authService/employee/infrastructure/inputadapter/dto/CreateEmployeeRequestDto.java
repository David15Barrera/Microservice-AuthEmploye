package com.serviceAuth.authService.employee.infrastructure.inputadapter.dto;

import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class CreateEmployeeRequestDto {

    @NotBlank
    private final String fullName;

    @NotBlank
    private final String cui;

    @NotBlank
    private final String phone;

    @Email
    private final String email;

    @NotBlank
    private final String jobPosition;

    @Positive
    private final BigDecimal salary;

    @NotBlank
    private final String address;

    private final UUID hotelId;
    private final UUID restaurantId;

    public CreateEmployeeDto toDomain() {
        return new CreateEmployeeDto(
                fullName,
                email,
                cui,
                phone,
                jobPosition,
                address,
                salary,
                hotelId,
                restaurantId
        );
    }

}
