package com.serviceAuth.authService.user.infrastructure.outputadapter.persistence;

import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.user.application.ports.output.persistence.FindingUserByEmailOutputPort;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.mapper.UserMapper;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.repository.UserDBRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserRepositoryOutputAdapter implements FindingUserByEmailOutputPort {

    private final UserDBRepository userDBRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntityDomain> findByEmail(String email) {
        return this.userDBRepository.findByEmail(email, UserDBEntity.class)
                .map(userMapper::toDomain);
    }
}
