package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper;

import com.serviceAuth.authService.employee.domain.model.Employee;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toDomain(EmployeeDBEntity employeeDBEntity){
        if (employeeDBEntity == null) return null;

        return new Employee(employeeDBEntity.getId(),
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

    public EmployeeDBEntity toDBEntity(Employee employee){

        if (employee == null) return null;

        return EmployeeDBEntity
                .builder()
                .id(employee.getId())
                .email(employee.getEmail())
                .cui(employee.getCui())
                .RestaurantId(employee.getRestaurantId())
                .address(employee.getAddress())
                .fullName(employee.getFullName())
                .salary(employee.getSalary())
                .jobPosition(employee.getJobPosition())
                .HotelId(employee.getHotelId())
                .build();

    }

}
