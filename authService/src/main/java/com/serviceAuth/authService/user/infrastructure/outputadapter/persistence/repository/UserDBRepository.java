package com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.repository;

import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserDBRepository extends JpaRepository<UserDBEntity, UUID> {

    <U> Optional<U> findByEmail(String email, Class<U> type);
    Optional<?> findUnknownById(long id, Class<?> type);

    Optional<UserDBEntity> findByEmailAndRole_name(String email, String roleName);
}
