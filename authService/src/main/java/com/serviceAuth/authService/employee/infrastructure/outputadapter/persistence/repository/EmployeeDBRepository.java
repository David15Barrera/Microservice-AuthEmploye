package com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.repository;

import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeDBRepository extends JpaRepository<EmployeeDBEntity, UUID> {

    Optional<EmployeeDBEntity> findByEmail(String email);

    Optional<EmployeeDBEntity> findByCui(String cui);

    List<EmployeeDBEntity> findAllByJobPositionNot(String jobPosition);
}
