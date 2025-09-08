package com.serviceAuth.authService.employee.domain.model;

import com.serviceAuth.authService.common.application.exception.EntityConflictUserType;
import io.micrometer.core.instrument.config.InvalidConfigurationException;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class EmployeeDomainEntity {

    private UUID id;
    private String fullName;
    private String cui;
    private String phone;
    private String email;
    private String jobPosition;
    private BigDecimal salary;
    private String address;
    private UUID hotelId;
    private UUID restaurantId;

    public EmployeeDomainEntity(UUID id, String fullName, String cui, String phone, String email, String jobPosition, BigDecimal salary, String address, UUID hotelId, UUID restaurantId) {
        this.id = id;
        this.fullName = fullName;
        this.cui = cui;
        this.phone = phone;
        this.email = email;
        this.jobPosition = jobPosition;
        this.salary = salary;
        this.address = address;
        this.hotelId = hotelId;
        this.restaurantId = restaurantId;
        this.validate();
    }

    public EmployeeDomainEntity(String fullName, String cui, String phone, String email, String jobPosition, BigDecimal salary, String address, UUID hotelId, UUID restaurantId) {
        this.fullName = fullName;
        this.cui = cui;
        this.phone = phone;
        this.email = email;
        this.jobPosition = jobPosition;
        this.salary = salary;
        this.address = address;
        this.hotelId = hotelId;
        this.restaurantId = restaurantId;
        this.validate();
    }

    private void validate() {
        Objects.requireNonNull(email, "Email no puede ser nulo");


        if (!jobPosition.equalsIgnoreCase("GERENTE")){
            if (this.restaurantId == null && this.hotelId == null) {
                throw new EntityConflictUserType("Asigne empleado a retaurante o un hotel");
            }

            if (this.restaurantId != null && this.hotelId != null) {
                throw new EntityConflictUserType("Asignar empleado a un hotel o aun restaurente, no dos a la vez");
            }

        }

        if (!(this.salary.compareTo(BigDecimal.ZERO)>0)){
            throw new InvalidConfigurationException("El salario tiene que ser mayor a 0");
        }
    }

    public boolean isAssignedToHotel() {
        return this.hotelId != null;
    }
}
