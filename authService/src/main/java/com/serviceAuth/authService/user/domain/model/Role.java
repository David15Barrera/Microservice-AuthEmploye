package com.serviceAuth.authService.user.domain.model;

import com.serviceAuth.authService.role.infrastructure.output.persistence.Entity.RoleDBEntity;
import lombok.Getter;

@Getter
public class Role {
    String name;
    long id;

    public Role(RoleDBEntity roleDBEntity){
        this.name = roleDBEntity.getName();
        this.id = roleDBEntity.getId();
    }

    public Role(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}
