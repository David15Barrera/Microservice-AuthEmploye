package com.serviceAuth.authService.employee.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.employee.application.ports.input.CreatingEmployeeInputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByCuiOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.StoringEmployeeOutputPort;
import com.serviceAuth.authService.employee.application.usecase.dto.CreateEmployeeDto;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
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
    public EmployeeDomainEntity createEmployee(CreateEmployeeDto createEmployeeDto) {

        // validar que no exista otro empleado con ese email
        if (this.findingEmployeeByEmailOutputPort.findByEmployeeByEmail(createEmployeeDto.getEmail()).isPresent()){
            throw new EntityAlreadyExistsException("Empleado ya existente con ese correo");
        }

        // validar que el cui del empleado no exista
        if (this.findingEmployeeByCuiOutputPort.findByEmployeeByCui(createEmployeeDto.getCui()).isPresent()){
            throw new EntityAlreadyExistsException("Empleado ya existente con ese CUI");
        }

        // crear empleado
        EmployeeDomainEntity newEmployee = createEmployeeDto.toDomain();

        // validaciones tambien son output ports, consultando micros servicio de hoteles y restaurantes
        if (newEmployee.isAssignedToHotel()){
            //TODO: validar que el hotel exista
        }else{
            //TODO: validar que el restaurante exista
        }

        // persistencia
        return this.storingEmployeeOutputPort.save(newEmployee);
    }
}
