package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.graph.RatingNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepositoryNeo4j extends Neo4jRepository<RatingNeo4j, Long> {
    @Query("MATCH (r:Rating) where r.rate = $rate RETURN r")
    Optional<RatingNeo4j> findByRate(@Param("rate") Double rate);
}
