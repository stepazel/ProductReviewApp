package com.isep.api.gateway.model.document;

import com.isep.api.gateway.model.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class RoleMongo {
    @Id
    private String authority;

    @DBRef
    private UserMongo user;

    public RoleMongo(String authority) {
        this.authority = authority;
    }

    public Role toDomainEntity() {
        return new Role(authority);
    }
}
