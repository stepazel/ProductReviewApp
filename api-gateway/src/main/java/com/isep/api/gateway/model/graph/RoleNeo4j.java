package com.isep.api.gateway.model.graph;

import com.isep.api.gateway.model.Role;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Role")
public class RoleNeo4j {
    @Id
    @Property
    private final String authority;

    @Relationship(type = "IS_ASSIGNED_TO", direction = Relationship.Direction.INCOMING)
    private UserNeo4j user;

    public RoleNeo4j(String authority) {
        this.authority = authority;
    }

    public Role toDomainEntity() {
        return new Role(authority);
    }
}
