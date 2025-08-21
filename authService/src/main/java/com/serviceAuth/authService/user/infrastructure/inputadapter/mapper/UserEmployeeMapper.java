package com.serviceAuth.authService.user.infrastructure.inputadapter.mapper;

import com.serviceAuth.authService.user.domain.model.UserEmployee;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserEmployeeResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserEmployeeMapper {

    public UserEmployeeResponseDto toResponseDto(UserEmployee userEmployee){
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
}
