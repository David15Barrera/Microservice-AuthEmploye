package com.serviceAuth.authService.user.infrastructure.inputadapter.rest;


import com.serviceAuth.authService.common.infrastructure.anotation.WebAdapter;
import com.serviceAuth.authService.user.application.ports.input.CreatingUserEmployeeInputPort;
import com.serviceAuth.authService.user.application.usecase.createuseremployee.CreateUserEmployeeDto;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserEmployeeRequest;
import com.serviceAuth.authService.user.infrastructure.inputadapter.dto.UserEmployeeResponseDto;
import com.serviceAuth.authService.user.infrastructure.inputadapter.mapper.UserEmployeeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth/employee")
@WebAdapter
@RequiredArgsConstructor
public class UserEmployeeControllerAdapter {

    private final CreatingUserEmployeeInputPort creatingUserEmployeeInputPort;
    private final UserEmployeeMapper userEmployeeMapper;

    @PostMapping("/sign-up")
    @Transactional
    public ResponseEntity<UserEmployeeResponseDto> creatRegisterUserEmployee(@RequestBody @Valid UserEmployeeRequest createUserRequest) {
        CreateUserEmployeeDto objectAdapterFromToDomain = createUserRequest.toDomain();

        UserEmployeeEntityDomain userEmployee = this.creatingUserEmployeeInputPort.createUserEmployee(objectAdapterFromToDomain);

        UserEmployeeResponseDto userEmployeeResponse = userEmployeeMapper.toResponseDto(userEmployee);

        return ResponseEntity.status(HttpStatus.CREATED).body(userEmployeeResponse);
    }

}
