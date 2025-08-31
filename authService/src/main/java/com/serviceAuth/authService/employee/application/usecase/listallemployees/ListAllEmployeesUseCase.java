package com.serviceAuth.authService.employee.application.usecase.listallemployees;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.employee.application.ports.input.ListingAllEmployeesInputPort;
import com.serviceAuth.authService.employee.application.ports.input.ListingAllEmployeesNoManagersInputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingAllEmployeesNoMangerOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingAllEmployeesOutputPort;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAllEmployeesUseCase implements ListingAllEmployeesNoManagersInputPort, ListingAllEmployeesInputPort {

    private final FindingAllEmployeesNoMangerOutputPort findingAllEmployeesNoMangerOutputPort;
    private final FindingAllEmployeesOutputPort findingAllEmployeesOutputPort;

    @Override
    public List<EmployeeDomainEntity> listAllEmployeesNoManagers() {
        return this.findingAllEmployeesNoMangerOutputPort.findAllEmployeesNoManger();
    }

    @Override
    public List<EmployeeDomainEntity> findAllEmployees() {
        return findingAllEmployeesOutputPort.findAllEmployees();
    }
}
