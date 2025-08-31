package com.serviceAuth.authService.customer.application.usecase;

import com.serviceAuth.authService.common.application.annotations.UseCase;
import com.serviceAuth.authService.customer.application.ports.input.ListingAllCustomersInputPort;
import com.serviceAuth.authService.customer.application.ports.output.FindingAllCustomersOutputPort;
import com.serviceAuth.authService.customer.domain.model.CustomerDomainEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListCustomersCase implements ListingAllCustomersInputPort {
    private final FindingAllCustomersOutputPort findingAll;
    @Override
    public List<CustomerDomainEntity> list() {
        return findingAll.findAll();
    }
}
