package com.serviceAuth.authService.employee.domain.model;

import com.serviceAuth.authService.common.application.exception.EntityConflictUserType;
import io.micrometer.core.instrument.config.InvalidConfigurationException;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
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

        // TODO: mas validaciones de dominio.

        if (!jobPosition.equalsIgnoreCase("GERENTE")){
            if (this.restaurantId == null && this.hotelId == null) {
                throw new EntityConflictUserType("El empleado debe estar asignado a un retaurante o un hotel");
            }

            if (this.restaurantId != null && this.hotelId != null) {
                throw new EntityConflictUserType("El empleado solo puede estar asignado a un hoter o aun restaurente, no los dos a la vez");
            }

        }

        if (!(this.salary.compareTo(BigDecimal.ZERO)>0)){
            throw new InvalidConfigurationException("El salario no debe ser mayor a cero");
        }
    }

    public boolean isAssignedToHotel() {
        return this.hotelId != null;
    }
}
