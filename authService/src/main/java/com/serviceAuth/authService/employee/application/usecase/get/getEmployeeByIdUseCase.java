package com.serviceAuth.authService.employee.application.usecase.get;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityNotFount;
import com.serviceAuth.authService.customer.application.ports.input.FindingCustomerByIdInputPort;
import com.serviceAuth.authService.employee.application.ports.input.FindingEmployeeByIdInputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByIdOutputPort;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class getEmployeeByIdUseCase implements FindingEmployeeByIdInputPort {

    private final FindingEmployeeByIdOutputPort findingEmployeeByIdOutputPort;

    @Override
    public EmployeeDomainEntity findById(UUID id){
        return findingEmployeeByIdOutputPort.findById(id).orElseThrow(() -> new EntityNotFount("Employee not found"));
    }

}
