package com.serviceAuth.authService.employee.infrastructure.inputadapter.rest;

import com.serviceAuth.authService.common.infrastructure.anotation.WebAdapter;
import com.serviceAuth.authService.employee.application.ports.input.*;
import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.CreateEmployeeRequestDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.CreateEmployeeResponseDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.dto.EmployeeResponseDto;
import com.serviceAuth.authService.employee.infrastructure.inputadapter.mapper.CreateEmployeeMapper;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper.EmployeeMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/employees")
@WebAdapter
@RequiredArgsConstructor
public class EmployeeControllerAdapter {

    private final CreatingEmployeeInputPort creatingEmployeeInputPort;
    private final CreateEmployeeMapper createEmployeeMapper;
    private final ListingAllEmployeesNoManagersInputPort listingAllEmployeesNoManagersInputPort;
    private final ListingAllEmployeesInputPort listingAllEmployeesInputPort;
    private final FindingEmployeeByIdInputPort findingEmployeeByIdInputPort;
    private final UpdatingEmployeeInputPort updatingEmployeeInputPort;

    @PostMapping()
    @Transactional
    public ResponseEntity<CreateEmployeeResponseDto> createUserEmployee(@RequestBody @Valid CreateEmployeeRequestDto createEmployeeRequestDto) {

        CreateEmployeeDto createEmployeeDto = createEmployeeRequestDto.toDomain();

        EmployeeDomainEntity employee = this.creatingEmployeeInputPort.createEmployee(createEmployeeDto);

        CreateEmployeeResponseDto createEmployeeResponseDto = this.createEmployeeMapper.toResponseDto(employee);

        return ResponseEntity.status(HttpStatus.CREATED).body(createEmployeeResponseDto);
    }


    @GetMapping("/all/no-manager")
    public ResponseEntity<List<EmployeeResponseDto>> findAllEmployeesNoManger() {
        List<EmployeeResponseDto> employees = this.listingAllEmployeesNoManagersInputPort.listAllEmployeesNoManagers()
                .stream()
                .map(createEmployeeMapper::toFindResponseDto)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping()
    public ResponseEntity<List<EmployeeResponseDto>> findAllEmployees() {
        List<EmployeeResponseDto> employees = this.listingAllEmployeesInputPort.findAllEmployees()
                .stream()
                .map(createEmployeeMapper::toFindResponseDto)
                .toList();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public EmployeeResponseDto get(@PathVariable("id") UUID id) {
        EmployeeDomainEntity employee = this.findingEmployeeByIdInputPort.findById(id);
        return createEmployeeMapper.toFindResponseDto(employee);
    }

    @PutMapping("/{id}")
    public EmployeeResponseDto update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CreateEmployeeRequestDto req) {

        return EmployeeMapper.toResponse(
                updatingEmployeeInputPort.update(
                        id,
                        req.getFullName(),
                        req.getCui(),
                        req.getPhone(),
                        req.getEmail(),
                        req.getJobPosition(),
                        req.getSalary(),
                        req.getAddress(),
                        req.getHotelId(),
                        req.getRestaurantId()
                ));
    }
}
