package com.isep.api.gateway.model.graph;

import com.isep.api.gateway.model.User;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.Set;
import java.util.stream.Collectors;

@Node("User")
@Getter
public class UserNeo4j {

    @Id
    @GeneratedValue
    private final Long userId;

    @Property
    private final String username;

    @Property
    private final String password;

    @Property
    private final String fullName;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private final Set<RoleNeo4j> roles;

    @Property
    private final String nif;

    @Property
    private final String morada;

//    @Relationship(type = "VOTED", direction = Relationship.Direction.OUTGOING)
//    private final List<ReviewNeo4j> reviews = new ArrayList<>();


    public UserNeo4j(Long userId, String username, String password, String fullName, String nif, String morada,
                     Set<RoleNeo4j> roles) {
        this.userId   = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif      = nif;
        this.morada   = morada;
        this.roles    = roles;
    }


    public User toDomainEntity() {
        var user = new User(username, password, fullName, nif, morada);
        user.setAuthorities(roles.stream().map(RoleNeo4j::toDomainEntity).collect(Collectors.toSet()));
        user.setUserId(userId);
        return user;
    }
}
