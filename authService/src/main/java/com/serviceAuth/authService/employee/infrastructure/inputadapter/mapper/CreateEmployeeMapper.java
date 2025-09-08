package com.serviceAuth.authService.employee.infrastructure.inputadapter.mapper;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.CreateEmployeeResponseDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.EmployeeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CreateEmployeeMapper {


    public CreateEmployeeResponseDto toResponseDto(EmployeeDomainEntity employee){
        if (employee == null) {
            return null;
        }

        return CreateEmployeeResponseDto.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .cui(employee.getCui())
                .phone(employee.getPhone())
                .jobPosition(employee.getJobPosition())
                .salary(employee.getSalary())
                .address(employee.getAddress())
                .hotelId(employee.getHotelId())
                .restaurantId(employee.getRestaurantId())
                .build();
    }

    public EmployeeResponseDto toFindResponseDto(EmployeeDomainEntity employee){
        if (employee == null) {
            return null;
        }

        String jobArea = employee.getJobPosition().equalsIgnoreCase("CAJERO") ? "RESTAURANT" : "HOTEL";

        return EmployeeResponseDto.builder()
                .id(employee.getId())
                .fullName(employee.getFullName())
                .email(employee.getEmail())
                .cui(employee.getCui())
                .phone(employee.getPhone())
                .jobPosition(employee.getJobPosition())
                .salary(employee.getSalary())
                .address(employee.getAddress())
                .hotelId(employee.getHotelId())
                .restaurantId(employee.getRestaurantId())

                .build();
    }

}
