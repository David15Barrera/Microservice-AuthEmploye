package com.serviceAuth.authService.user.application.usecase.createuseremployee;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.domain.model.Employee;
import com.serviceAuth.authService.user.application.ports.input.CreatingUserEmployeeInputPort;
import com.serviceAuth.authService.user.application.ports.output.notification.ConfirmationRegisterUseNotificationPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.FindingUserEmployeeByEmailAndRoleOutputPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.StoringUserEmployeeOutputPort;
import com.serviceAuth.authService.user.domain.model.UserEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@UseCase
@Validated
@RequiredArgsConstructor
public class CreateUserEmployeeCase implements CreatingUserEmployeeInputPort {

    private final StoringUserEmployeeOutputPort storingAuthorOutputPort;
    private final FindingUserEmployeeByEmailAndRoleOutputPort findingUserEmployeeByEmailAndRoleOutputPort;
    private final ConfirmationRegisterUseNotificationPort confirmationRegisterUseNotificationPort;
    private final FindingEmployeeByEmailOutputPort findingEmployeeByEmailOutputPort;


    @Override
    @Transactional
    public UserEmployee createUserEmployee(CreateUserEmployeeDto createUserEmployeeDto) {

        // validar si ya existe un empleado con ese email
        if (findingUserEmployeeByEmailAndRoleOutputPort.findByEmailAndRole(createUserEmployeeDto.getEmail(), createUserEmployeeDto.getRole()).isPresent()){
            throw new EntityAlreadyExistsException("Usuario ya existente con ese correo");
        }

        // validar si el empleado existe
        Employee employee = findingEmployeeByEmailOutputPort
                .findByEmployeeByEmail(createUserEmployeeDto.getEmail())
                .orElseThrow(() -> new EntityAlreadyExistsException("El email no está relacionado con un empleado"));


        //crear el usuario empleado
        UserEmployee newUserEmployee = createUserEmployeeDto.toDomain(employee.getId());

        // persistencia
        UserEmployee savedUserEmployee = storingAuthorOutputPort.save(newUserEmployee, employee);

        // enviar correo notificacion
        confirmationRegisterUseNotificationPort.notifyConfirmRegister(savedUserEmployee);

        return savedUserEmployee;
    }
}
