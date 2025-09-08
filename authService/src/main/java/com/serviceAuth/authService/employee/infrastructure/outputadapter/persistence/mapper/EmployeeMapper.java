package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.EmployeeResponseDto;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDomainEntity toDomain(EmployeeDBEntity entity){
        if (entity == null) return null;

        return new EmployeeDomainEntity(entity.getId(),
                entity.getFullName(),
                entity.getCui(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getJobPosition(),
                entity.getSalary(),
                entity.getAddress(),
                entity.getHotelId(),
                entity.getRestaurantId());
    }

    public EmployeeDBEntity toDBEntity(EmployeeDomainEntity employee){

        if (employee == null) return null;

        return EmployeeDBEntity
                .builder()
                .email(employee.getEmail())
                .cui(employee.getCui())
                .RestaurantId(employee.getRestaurantId())
                .address(employee.getAddress())
                .fullName(employee.getFullName())
                .salary(employee.getSalary())
                .phone(employee.getPhone())
                .jobPosition(employee.getJobPosition())
                .HotelId(employee.getHotelId())
                .build();
    }

    public static EmployeeResponseDto toResponse(EmployeeDomainEntity employee){
        return new EmployeeResponseDto(employee.getId(),
                employee.getFullName(),
                employee.getCui(),
                employee.getPhone(),
                employee.getEmail(),
                employee.getJobPosition(),
                employee.getSalary(),
                employee.getAddress(),
                employee.getHotelId(),
                employee.getRestaurantId());
    }
}
