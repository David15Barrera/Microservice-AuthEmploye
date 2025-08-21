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
    private String cui;

    @NotBlank
    private String phone;

    @Email
    private String email;

    @NotBlank
    private String jobPosition;

    @Positive
    private BigDecimal salary;

    @NotBlank
    private String address;

    private UUID hotelId;
    private UUID restaurantId;

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
