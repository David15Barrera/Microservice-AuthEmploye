package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence;


import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByCuiOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.FindingEmployeeByEmailOutputPort;
import com.serviceAuth.authService.employee.application.ports.output.StoringEmployeeOutputPort;
import com.serviceAuth.authService.employee.domain.model.Employee;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.mapper.EmployeeMapper;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.repository.EmployeeDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeeRepositoryOutputAdapter implements FindingEmployeeByEmailOutputPort, FindingEmployeeByCuiOutputPort, StoringEmployeeOutputPort {

    private final EmployeeDBRepository employeeDBRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByEmployeeByEmail(String email) {
        return this.employeeDBRepository.findByEmail(email)
                .map(employeeMapper::toDomain);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {

        EmployeeDBEntity employeeDBEntity = this.employeeDBRepository.save(this.employeeMapper.toDBEntity(employee));

        Employee employeeSaved = this.employeeMapper.toDomain(employeeDBEntity);

        return employeeSaved;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByEmployeeByCui(String cui) {
        return this.employeeDBRepository.findByCui(cui)
                .map(employeeMapper::toDomain);
    }
}
