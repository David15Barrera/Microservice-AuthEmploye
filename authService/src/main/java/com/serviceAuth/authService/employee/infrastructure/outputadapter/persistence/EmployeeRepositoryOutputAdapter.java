package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence;

import com.serviceAuth.authService.common.application.exception.EntityNotFount;
import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.employee.application.ports.input.UpdatingEmployeeInputPort;
import com.serviceAuth.authService.employee.application.ports.output.*;
import com.serviceAuth.authService.employee.domain.model.EmployeeDomainEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper.EmployeeMapper;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.repository.EmployeeDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeeRepositoryOutputAdapter implements FindingEmployeeByEmailOutputPort,
        FindingEmployeeByCuiOutputPort, StoringEmployeeOutputPort,
        FindingAllEmployeesNoMangerOutputPort, FindingAllEmployeesOutputPort,
        FindingEmployeeByIdOutputPort {

    private final EmployeeDBRepository employeeDBRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDomainEntity> findByEmployeeByEmail(String email) {
        return this.employeeDBRepository.findByEmail(email)
                .map(employeeMapper::toDomain);
    }

    @Override
    public EmployeeDomainEntity save(EmployeeDomainEntity employee) {
        EmployeeDBEntity dbEntity;

        if (employee.getId() != null) {
            dbEntity = employeeDBRepository.findById(employee.getId())
                    .orElseThrow(() -> new RuntimeException("employee not found"));

            dbEntity.setFullName(employee.getFullName());
            dbEntity.setCui(employee.getCui());
            dbEntity.setPhone(employee.getPhone());
            dbEntity.setEmail(employee.getEmail());
            dbEntity.setJobPosition(employee.getJobPosition());
            dbEntity.setSalary(employee.getSalary());
            dbEntity.setAddress(employee.getAddress());
            dbEntity.setHotelId(employee.getHotelId());
            dbEntity.setRestaurantId(employee.getRestaurantId());

        } else {
            dbEntity = employeeMapper.toDBEntity(employee);
        }

        EmployeeDBEntity saved = employeeDBRepository.save(dbEntity);
        return employeeMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmployeeDomainEntity> findByEmployeeByCui(String cui) {
        return this.employeeDBRepository.findByCui(cui)
                .map(employeeMapper::toDomain);
    }

    @Override
    public Optional<EmployeeDomainEntity> findById(UUID id) {
        return  this.employeeDBRepository.findById(id)
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

