package com.serviceAuth.authService.customer.infrastructure.inputadapter.rest;

import com.serviceAuth.authService.common.infrastructure.anotation.WebAdapter;
import com.serviceAuth.authService.customer.application.ports.input.*;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import com.serviceAuth.authService.customer.infrastructure.inputadapter.dto.CreateCustomerRequestDto;
import com.serviceAuth.authService.customer.infrastructure.inputadapter.dto.CustomerResponseDto;
import com.serviceAuth.authService.customer.infrastructure.inputadapter.mapper.CustomerDtoMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@WebAdapter
@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerControllerAdapter {
    private final CreatingCustomerInputPort creating;
    private final ListingAllCustomersInputPort listing;
    private final FindingCustomerByIdInputPort findingById;
    private final UpdatingCustomerInputPort updating;
    private final DeletingCustomerInputPort deleting;
    private final CustomerDtoMapper mapper;

    @PostMapping
    public ResponseEntity<CustomerResponseDto> create(@Valid @RequestBody CreateCustomerRequestDto req) {
        CustomerDomainEntity created = creating.create(mapper.toCreateDto(req));
        return new ResponseEntity<>(mapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CustomerResponseDto> list() {
        return listing.list().stream().map(mapper::toResponse).toList();
    }

    @GetMapping("/{id}")
    public CustomerResponseDto get(@PathVariable("id") UUID id) {
        return mapper.toResponse(findingById.findById(id));
    }

    @PutMapping("/{id}")
    public CustomerResponseDto update(
            @PathVariable("id") UUID id,
            @Valid @RequestBody CreateCustomerRequestDto req) {
        return mapper.toResponse(updating.update(
                id,
                req.getFullName(),
                req.getCui(),
                req.getPhone(),
                req.getEmail(),
                req.getAddress(),
                req.getLoyaltyPoints()
        ));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id) {
        deleting.delete(id);
    }
}
