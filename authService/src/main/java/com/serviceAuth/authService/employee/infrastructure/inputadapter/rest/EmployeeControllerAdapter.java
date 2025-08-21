package com.serviceAuth.authService.employee.infrastructure.inputadapter.rest;

import com.serviceAuth.authService.common.infrastructure.anotation.WebAdapter;
import com.serviceAuth.authService.employee.application.ports.input.CreatingEmployeeInputPort;
import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.Employee;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.CreateEmployeeRequestDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.CreateEmployeeResponseDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.mapper.CreateEmployeeMapper;
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
@RequestMapping("v1/employee/")
@WebAdapter
@RequiredArgsConstructor
public class EmployeeControllerAdapter {

    private final CreatingEmployeeInputPort creatingEmployeeInputPort;
    private final CreateEmployeeMapper createEmployeeMapper;

    @PostMapping()
    @Transactional
    public ResponseEntity<CreateEmployeeResponseDto> createUserEmployee(@RequestBody @Valid CreateEmployeeRequestDto createEmployeeRequestDto) {

        // Convert the request DTO to a domain object
        CreateEmployeeDto createEmployeeDto = createEmployeeRequestDto.toDomain();

        Employee employee = this.creatingEmployeeInputPort.createEmployee(createEmployeeDto);

        CreateEmployeeResponseDto createEmployeeResponseDto = this.createEmployeeMapper.toResponseDto(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployeeResponseDto);


    }
}
