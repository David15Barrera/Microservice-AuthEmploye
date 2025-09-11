package com.serviceAuth.authService.employee.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
public class RestaurantDomainEntity {
    private UUID id;
    private String name;
    private UUID hotelId;
    private String address;
    private String phone;
    private Integer capacity;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private OffsetDateTime createdAt;
}
