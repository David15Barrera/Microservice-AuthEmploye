package com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.mapper;

import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.entity.CustomerDBEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public CustomerDomainEntity toDomain(CustomerDBEntity entity) {
        if (entity == null) return null;
        return new CustomerDomainEntity(entity.getId(), entity.getFullName(), entity.getCui(), entity.getPhone(), entity.getEmail(), entity.getAddress(), entity.getLoyaltyPoints());
    }
    public CustomerDBEntity toDB(CustomerDomainEntity domain) {
        if (domain == null) return null;
        return CustomerDBEntity.builder()
                .id(domain.getId())
                .fullName(domain.getFullName())
                .cui(domain.getCui())
                .phone(domain.getPhone())
                .email(domain.getEmail())
                .address(domain.getAddress())
                .loyaltyPoints(domain.getLoyaltyPoints())
                .build();
    }
}
