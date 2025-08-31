package com.serviceAuth.authService.user.application.ports.input;

import com.serviceAuth.authService.user.application.usecase.createuseremployee.CreateUserEmployeeDto;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import jakarta.validation.Valid;

public interface CreatingUserEmployeeInputPort {
    UserEmployeeEntityDomain createUserEmployee(@Valid CreateUserEmployeeDto createUserEmployeeDto);
}
