package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence;

import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.employee.application.ports.output.*;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper.EmployeeMapper;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.repository.EmployeeDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeeRepositoryOutputAdapter implements FindingEmployeeByEmailOutputPort, FindingEmployeeByCuiOutputPort,
        StoringEmployeeOutputPort, FindingAllEmployeesNoMangerOutputPort, FindingAllEmployeesOutputPort {

    private final EmployeeDBRepository employeeDBRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDomainEntity> findByEmployeeByEmail(String email) {
        return this.employeeDBRepository.findByEmail(email)
                .map(employeeMapper::toDomain);
    }

    @Override
    @Transactional
    public EmployeeDomainEntity save(EmployeeDomainEntity employee) {

        EmployeeDBEntity employeeDBEntity = this.employeeDBRepository.save(this.employeeMapper.toDBEntity(employee));

        return this.employeeMapper.toDomain(employeeDBEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDomainEntity> findByEmployeeByCui(String cui) {
        return this.employeeDBRepository.findByCui(cui)
                .map(employeeMapper::toDomain);
    }

    @Override
    public List<EmployeeDomainEntity> findAllEmployeesNoManger() {
        return this.employeeDBRepository.findAllByJobPositionNot("GERENTE")
                .stream()
                .map(employeeMapper::toDomain)
                .toList();
    }

    @Override
    public List<EmployeeDomainEntity> findAllEmployees() {
        return this.employeeDBRepository.findAll()
                .stream()
                .map(employeeMapper::toDomain)
                .toList();
    }
}
