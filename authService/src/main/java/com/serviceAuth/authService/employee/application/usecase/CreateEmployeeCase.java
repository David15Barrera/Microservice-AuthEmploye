package com.serviceAuth.authService.employee.application.usecase;


import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.employee.application.ports.input.CreatingEmployeeInputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByCuiOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.StoringEmployeeOutputPort;
import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@UseCase
@Validated
@RequiredArgsConstructor
public class CreateEmployeeCase implements CreatingEmployeeInputPort {

    private final StoringEmployeeOutputPort storingEmployeeOutputPort;
    private final FindingEmployeeByEmailOutputPort findingEmployeeByEmailOutputPort;
    private final FindingEmployeeByCuiOutputPort findingEmployeeByCuiOutputPort;

    @Override
    @Transactional
    public Employee createEmployee(CreateEmployeeDto createEmployeeDto) {

        // validar que no exista otro empleado con ese email
        if (this.findingEmployeeByEmailOutputPort.findByEmployeeByEmail(createEmployeeDto.getEmail()).isPresent()){
            throw new EntityAlreadyExistsException("Empleado ya existente con ese correo");
        }

        // validar cui
        if (this.findingEmployeeByCuiOutputPort.findByEmployeeByCui(createEmployeeDto.getCui()).isPresent()){
            throw new EntityAlreadyExistsException("Empleado ya existente con ese CUI");
        }

        // crear empleado
        Employee newEmployee = createEmployeeDto.toDomain();

        // persistencia
        Employee savedEmployee = this.storingEmployeeOutputPort.save(newEmployee);

        return savedEmployee;
    }
}
