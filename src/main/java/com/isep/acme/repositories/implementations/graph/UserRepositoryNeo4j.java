package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.graph.UserNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface UserRepositoryNeo4j extends Neo4jRepository<UserNeo4j, Long> {
    Optional<UserNeo4j> findByUserId(Long id);

    Optional<UserNeo4j> findByUsername(String username);
}
