package com.isep.acme.model.graph;

import com.isep.acme.model.Role;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Node
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
    private Set<Role> roles = new HashSet<>();

    @Property
    private String nif;

    @Property
    private String morada;

    @Relationship(type = "IS_AUTHOR_OF", direction = Relationship.Direction.OUTGOING)
    private List<ReviewNeo4j> reviews = new ArrayList<ReviewNeo4j>();
}
