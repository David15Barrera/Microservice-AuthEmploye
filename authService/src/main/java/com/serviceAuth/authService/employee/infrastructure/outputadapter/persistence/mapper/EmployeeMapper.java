package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper;

import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeDomainEntity toDomain(EmployeeDBEntity employeeDBEntity){
        if (employeeDBEntity == null) return null;

        return new EmployeeDomainEntity(employeeDBEntity.getId(),
                employeeDBEntity.getFullName(),
                employeeDBEntity.getCui(),
                employeeDBEntity.getPhone(),
                employeeDBEntity.getEmail(),
                employeeDBEntity.getJobPosition(),
                employeeDBEntity.getSalary(),
                employeeDBEntity.getAddress(),
                employeeDBEntity.getHotelId(),
                employeeDBEntity.getRestaurantId());
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

}
