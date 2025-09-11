package com.serviceAuth.authService.employee.infrastructure.inputadapter.dto;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;

public class RestaurantResponse {
    public UUID id;
    public String name;
    public UUID hotelId;
    public String address;
    public String phone;
    public Integer capacity;
    public LocalTime openingTime;
    public LocalTime closingTime;
    public OffsetDateTime createdAt;
}
