package com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.repository;

import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.entity.CustomerDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerDBRepository extends JpaRepository<CustomerDBEntity, UUID> {
    Optional<CustomerDBEntity> findByEmailIgnoreCase(String email);
    Optional<CustomerDBEntity> findByCuiIgnoreCase(String cui);
}
