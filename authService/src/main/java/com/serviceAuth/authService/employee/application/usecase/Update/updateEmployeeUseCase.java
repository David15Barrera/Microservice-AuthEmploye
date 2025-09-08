package com.serviceAuth.authService.employee.application.usecase.Update;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.common.application.exception.EntityNotFount;
import com.serviceAuth.authService.employee.application.ports.input.UpdatingEmployeeInputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByCuiOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByIdOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.StoringEmployeeOutputPort;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@UseCase
@RequiredArgsConstructor
public class updateEmployeeUseCase implements UpdatingEmployeeInputPort {

    private final StoringEmployeeOutputPort storing;
    private final FindingEmployeeByIdOutputPort findById;
    private final FindingEmployeeByEmailOutputPort findByEmail;
    private final FindingEmployeeByCuiOutputPort findByCui;


    @Override
    @Transactional
    public EmployeeDomainEntity update(UUID id,
                                       String fullName,
                                       String cui,
                                       String phone,
                                       String email,
                                       String jobPosition,
                                       BigDecimal salary,
                                       String address,
                                       UUID hotelId,
                                       UUID restaurantId) {

        // 1. Encuentra el empleado actual.
        EmployeeDomainEntity current = findById.findById(id).orElseThrow(() -> new EntityNotFount("Employee not found"));

        // 2. Valida la unicidad del email y del CUI. Esta lógica está correcta.
        if (!current.getEmail().equalsIgnoreCase(email)) {
            findByEmail.findByEmployeeByEmail(email).ifPresent(e -> {
                if (!e.getId().equals(id)) {
                    throw new EntityAlreadyExistsException("Email already in use");
                }
            });
        }

        if (!current.getCui().equalsIgnoreCase(cui)) {
            findByCui.findByEmployeeByCui(cui).ifPresent(e -> {
                if (!e.getId().equals(id)) {
                    throw new EntityAlreadyExistsException("CUI already in use");
                }
            });
        }

        EmployeeDomainEntity merged = new EmployeeDomainEntity(
                id,
                fullName,
                cui,
                phone,
                email,
                jobPosition,
                salary,
                address,
                hotelId,
                restaurantId
        );

        return storing.save(merged);
    }
}