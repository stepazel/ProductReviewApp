package com.isep.acme.model.jpa;

import com.isep.acme.model.Role;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
@AllArgsConstructor
public class RoleJpa implements GrantedAuthority {
    String authority;

    public Role toDomainEntity() {
        return new Role(authority);
    }
}
