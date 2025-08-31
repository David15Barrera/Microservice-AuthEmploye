package com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity;

import com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.entity.CustomerDBEntity;
import com.serviceAuth.authService.employee.infrastructure.outputadapter.persistence.entity.EmployeeDBEntity;
import com.serviceAuth.authService.role.infrastructure.output.persistence.Entity.RoleDBEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "user")
@Table(name = "user", schema = "auth")
@Data
@Builder(toBuilder = true)
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor(access = PRIVATE)
public class    UserDBEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "UUID DEFAULT uuid_generate_v4()")
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @ManyToOne(optional = true)
    @JoinColumn(name = "employee_id")
    private EmployeeDBEntity employee;

    @ManyToOne(optional = true)
    @JoinColumn(name = "customer_id")
    private CustomerDBEntity customer;

    private boolean active;

    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "role_id")
    private RoleDBEntity role;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

}
