package com.serviceAuth.authService.user.infrastructure.inputadapter.mapper;

import com.serviceAuth.authService.user.domain.model.UserCustomerEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserEmployeeResponseDto;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserEmployeeMapper {

    public UserEmployeeResponseDto toResponseDto(UserEmployeeEntityDomain userEmployee){
        if (userEmployee == null) {
            return null;
        }

        return UserEmployeeResponseDto.builder()
                .id(userEmployee.getId())
                .employeeId(userEmployee.getEmployeeId())
                .role(userEmployee.getRole())
                .email(userEmployee.getEmail())
                .active(userEmployee.isActive())
                .build();
    }

    public UserResponseDto toResponseDto(UserEntityDomain userEntityDomain){
        if(userEntityDomain == null){
            return null;
        }

        String employeeId = (userEntityDomain instanceof UserEmployeeEntityDomain)
                ? ((UserEmployeeEntityDomain) userEntityDomain).getEmployeeId().toString() : null;

        String customerId = (userEntityDomain instanceof UserCustomerEntityDomain)
                ? ((UserCustomerEntityDomain) userEntityDomain).getCustomerId().toString() : null;


        return UserResponseDto.builder()
                .id(userEntityDomain.getId().toString())
                .employeeId(employeeId)
                .customerId(customerId)
                .email(userEntityDomain.getEmail())
                .active(userEntityDomain.isActive())
                .roleName(userEntityDomain.getRole().getName())
                .build();
    }
}
