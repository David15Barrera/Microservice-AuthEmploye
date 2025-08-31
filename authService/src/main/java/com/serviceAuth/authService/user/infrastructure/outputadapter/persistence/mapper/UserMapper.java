package com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.mapper;

import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.role.infrastructure.output.persistence.Entity.RoleDBEntity;
import com.serviceAuth.authService.user.domain.model.Role;
import com.serviceAuth.authService.user.domain.model.UserCustomerEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEmployeeEntityDomain;
import com.serviceAuth.authService.user.domain.model.UserEntityDomain;
import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntityDomain toDomain(UserDBEntity userDBEntity){
        if (userDBEntity == null) return null;

        if ( userDBEntity.getRole().getName().equalsIgnoreCase("CUSTOMER")){
            return this.toDomainUserCustomer(userDBEntity);
        }

        return this.toDomainUserEmployee(userDBEntity);
    }

    public UserEmployeeEntityDomain toDomainUserEmployee(UserDBEntity userDBEntity){

        if (userDBEntity == null) return null;

        return new UserEmployeeEntityDomain(userDBEntity.getId(),
                userDBEntity.getEmployee().getId(),
                userDBEntity.getEmail(), userDBEntity.isActive(), new Role(userDBEntity.getRole()), userDBEntity.getPassword());
    }

    public UserCustomerEntityDomain toDomainUserCustomer(UserDBEntity userDBEntity){

        if (userDBEntity == null) return null;

        return new UserCustomerEntityDomain(userDBEntity.getId(),
                userDBEntity.getCustomer().getId(),
                userDBEntity.getEmail(), userDBEntity.isActive(), new Role(userDBEntity.getRole()), userDBEntity.getPassword());
    }

    public UserDBEntity toDBEntity(UserEmployeeEntityDomain userEmployee, RoleDBEntity role, EmployeeDBEntity employeeDBEntity){
        if (userEmployee == null) return null;

        return UserDBEntity.builder()
                .email(userEmployee.getEmail())
                .employee(employeeDBEntity)
                .customer(null)
                .active(true)
                .password(userEmployee.getPassword())
                .role(role)
                .build();
    }
}
