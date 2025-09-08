package com.serviceAuth.authService.customer.infrastructure.inputadapter.mapper;

import com.serviceAuth.authService.customer.application.usecase.dto.CreateCustomerDto;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import com.serviceAuth.authService.customer.infrastructure.inputadapter.dto.CreateCustomerRequestDto;
import com.serviceAuth.authService.customer.infrastructure.inputadapter.dto.CustomerResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {
    public CreateCustomerDto toCreateDto(CreateCustomerRequestDto req) {
        return new CreateCustomerDto(req.getFullName(), req.getCui(), req.getPhone(), req.getEmail(), req.getAddress(), req.getLoyaltyPoints());
    }
    public CustomerResponseDto toResponse(CustomerDomainEntity customer) {
        return new CustomerResponseDto(customer.getId(),
                customer.getFullName(),
                customer.getCui(),
                customer.getPhone(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getLoyaltyPoints());
    }
}
