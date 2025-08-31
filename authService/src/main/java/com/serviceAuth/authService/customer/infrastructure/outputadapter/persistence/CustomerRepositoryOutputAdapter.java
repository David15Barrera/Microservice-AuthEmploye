package com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence;

import com.serviceAuth.authService.common.infrastructure.anotation.PersistenceAdapter;
import com.serviceAuth.authService.customer.application.ports.output.*;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.entity.CustomerDBEntity;
import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.mapper.CustomerMapper;
import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.repository.CustomerDBRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@PersistenceAdapter
@RequiredArgsConstructor
public class CustomerRepositoryOutputAdapter implements StoringCustomerOutputPort, FindingAllCustomersOutputPort, FindingCustomerByIdOutputPort, FindingCustomerByEmailOutputPort, FindingCustomerByCuiOutputPort, DeletingCustomerOutputPort {
    private final CustomerDBRepository repo;
    private final CustomerMapper mapper;

    @Override
    public CustomerDomainEntity save(CustomerDomainEntity customer) {
        CustomerDBEntity dbEntity;

        if (customer.getId() != null) {
            // Operación de actualización
            dbEntity = repo.findById(customer.getId()).orElseThrow(() -> new RuntimeException("Customer not found"));
            dbEntity.setFullName(customer.getFullName());
            dbEntity.setCui(customer.getCui());
            dbEntity.setPhone(customer.getPhone());
            dbEntity.setEmail(customer.getEmail());
            dbEntity.setAddress(customer.getAddress());
            dbEntity.setLoyaltyPoints(customer.getLoyaltyPoints());
            // createdAt no se toca, updated_at se actualizará con @UpdateTimestamp
        } else {
            // Operación de creación
            dbEntity = mapper.toDB(customer);
        }

        CustomerDBEntity saved = repo.save(dbEntity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<CustomerDomainEntity> findAll() {
        return repo.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<CustomerDomainEntity> findById(UUID id) {
        return repo.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<CustomerDomainEntity> findByEmail(String email) {
        return repo.findByEmailIgnoreCase(email).map(mapper::toDomain);
    }

    @Override
    public Optional<CustomerDomainEntity> findByCui(String cui) {
        return repo.findByCuiIgnoreCase(cui).map(mapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repo.deleteById(id);
    }
}
