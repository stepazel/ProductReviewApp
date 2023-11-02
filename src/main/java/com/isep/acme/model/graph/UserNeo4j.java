package com.isep.acme.model.graph;

import com.isep.acme.model.Role;
import com.isep.acme.model.User;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Node("User")
@Getter
public class UserNeo4j {

    @Id
    @GeneratedValue
    private Long userId;

    @Property
    private String username;

    @Property
    private String password;

    @Property
    private String fullName;

    @Relationship(type = "HAS", direction = Relationship.Direction.OUTGOING)
    private Set<RoleNeo4j> roles;

    @Property
    private String nif;

    @Property
    private String morada;

    @Relationship(type = "VOTED", direction = Relationship.Direction.OUTGOING)
    private List<ReviewNeo4j> reviews = new ArrayList<>();


    public UserNeo4j(Long userId, String username, String password, String fullName, String nif, String morada, Set<RoleNeo4j> roles) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif = nif;
        this.morada = morada;
        this.roles = roles;
    }


    public User toDomainEntity() {
        var user =  new User(username, password, fullName, nif, morada);
        user.setAuthorities(roles.stream().map(RoleNeo4j::toDomainEntity).collect(Collectors.toSet()));
        user.setUserId(userId);
        return user;
    }
}
