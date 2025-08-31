package com.serviceAuth.authService.customer.infrastructure.outputadapter.persistence.entity;

import com.serviceAuth.authService.user.infrastructure.outputadapter.persistence.entity.UserDBEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "customer", schema = "identity")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDBEntity {
    @Id
    @Column(columnDefinition = "uuid")
    private UUID id;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
    }

    @Column(nullable = false, length = 150)
    private String fullName;

    @Column(nullable = false, unique = true, length = 30)
    private String cui;

    @Column(length = 20)
    private String phone;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 250)
    private String address;

    @Column
    private Integer loyaltyPoints;

    @OneToMany(mappedBy = "customer")
    private Set<UserDBEntity> users;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
