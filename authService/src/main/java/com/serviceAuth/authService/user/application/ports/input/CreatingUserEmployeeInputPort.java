package com.serviceAuth.authService.user.application.ports.input;

import com.serviceAuth.authService.user.application.usecase.createuseremployee.CreateUserEmployeeDto;
import com.serviceAuth.authService.user.domain.model.UserEmployee;
import jakarta.validation.Valid;

public interface CreatingUserEmployeeInputPort {
    UserEmployee createUserEmployee(@Valid CreateUserEmployeeDto createUserEmployeeDto);
}













