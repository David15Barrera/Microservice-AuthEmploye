package com.serviceAuth.authService.user.infrastructure.outputadapter.persistence;

import com.serviceAuth.authService.common.application.exception.RoleNotExist;
import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper.EmployeeMapper;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.repository.EmployeeDBRepository;
import com.serviceAuth.authService.role.infrastructure.output.persistence.Entity.RoleDBEntity;
import com.serviceAuth.authService.role.infrastructure.output.persistence.Repository.RoleDBRepository;
import com.serviceAuth.authService.user.application.ports.output.persistence.FindingUserEmployeeByEmailAndRoleOutputPort;
import com.serviceAuth.authService.user.application.ports.output.persistence.StoringUserEmployeeOutputPort;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.mapper.UserMapper;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserEmployeeRepositoryOutputAdapter implements FindingUserEmployeeByEmailAndRoleOutputPort, StoringUserEmployeeOutputPort {

    private final UserDBRepository userDBRepository;
    private final UserMapper userMapper;
    private final RoleDBRepository roleDBRepository;
    private final EmployeeDBRepository employeeDBRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEmployeeEntityDomain> findByEmailAndRole(String email, String role) {
        return this.userDBRepository.findByEmailAndRole_name(email,role)
                .map(userMapper::toDomainUserEmployee);
    }

    @Override
    @Transactional
    public UserEmployeeEntityDomain save(UserEmployeeEntityDomain userEmployee, EmployeeDomainEntity employee) {
        RoleDBEntity roleDBEntity = this.roleDBRepository.findByName(userEmployee.getRole().getName())
                .orElseThrow(()-> new RoleNotExist("No existe el rol para el empleado"));

        EmployeeDBEntity employeeDBEntity = this.employeeDBRepository.findByCui(employee.getCui())
                .orElseThrow(()-> new RoleNotExist("No existe el empleado"));

        UserDBEntity userDBEntity = this.userDBRepository.save(this.userMapper.toDBEntity(userEmployee, roleDBEntity, employeeDBEntity));

        return this.userMapper.toDomainUserEmployee(userDBEntity);
    }
}
