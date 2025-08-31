package com.serviceAuth.authService.user.application.usecase.createuseremployee;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.common.application.exception.EntityAlreadyExistsException;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByCuiOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.user.application.ports.input.CreatingUserEmployeeInputPort;
import com.serviceAuth.authService.user.application.ports.output.notification.ConfirmationRegisterUseNotificationPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.FindingUserEmployeeByEmailAndRoleOutputPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.StoringUserEmployeeOutputPort;
import com.serviceAuth.authService.user.application.ports.output.security.encript.PasswordEncoderPort;
import com.serviceAuth.authService.user.domain.model.Role;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@UseCase
@Validated
@RequiredArgsConstructor
public class CreateUserEmployeeCase implements CreatingUserEmployeeInputPort {

    private final StoringUserEmployeeOutputPort storingUserEmployeeOutputPort;
    private final FindingUserEmployeeByEmailAndRoleOutputPort findingUserEmployeeByEmailAndRoleOutputPort;
    private final ConfirmationRegisterUseNotificationPort confirmationRegisterUseNotificationPort;
    private final FindingEmployeeByEmailOutputPort findingEmployeeByEmailOutputPort;
    private final FindingEmployeeByCuiOutputPort findingEmployeeByCuiOutputPort;
    private final PasswordEncoderPort passwordEncoderPort;


    @Override
    @Transactional
    public UserEmployeeEntityDomain createUserEmployee(CreateUserEmployeeDto createUserEmployeeDto) {

        // validar si existe un empleado con ese cui
        if (this.findingEmployeeByCuiOutputPort.findByEmployeeByCui(createUserEmployeeDto.getCui()).isEmpty()){
            throw new EntityAlreadyExistsException("No existe un empleado con ese cui");
        }

        // validar si el empleado existe
        EmployeeDomainEntity employee = findingEmployeeByEmailOutputPort
                .findByEmployeeByEmail(createUserEmployeeDto.getEmail())
                .orElseThrow(() -> new EntityAlreadyExistsException("El email no está relacionado con un empleado"));


        // validar si ya existe un empleado con ese email
        if (findingUserEmployeeByEmailAndRoleOutputPort.findByEmailAndRole(createUserEmployeeDto.getEmail(), employee.getJobPosition()).isPresent()){
            throw new EntityAlreadyExistsException("Usuario ya existente con ese correo");
        }

        //crear el usuario empleado
        UserEmployeeEntityDomain newUserEmployee = createUserEmployeeDto.toDomain(employee.getId(), new Role(employee.getJobPosition()));

        // encriptar la contrass
        newUserEmployee.setPassword(passwordEncoderPort.encode(newUserEmployee.getPassword()));

        // persistencia
        UserEmployeeEntityDomain savedUserEmployee = storingUserEmployeeOutputPort.save(newUserEmployee, employee);

        // enviar correo notificacion
        confirmationRegisterUseNotificationPort.notifyConfirmRegister(savedUserEmployee);

        return savedUserEmployee;
    }
}
