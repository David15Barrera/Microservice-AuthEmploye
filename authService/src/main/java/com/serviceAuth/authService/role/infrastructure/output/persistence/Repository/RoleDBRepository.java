package com.serviceAuth.authService.role.infrastructure.output.persistence.Repository;

import com.serviceAuth.authService.role.infrastructure.output.persistence.Entity.RoleDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDBRepository extends JpaRepository<RoleDBEntity, Long> {

    Optional<RoleDBEntity> findByName(String name);
}
