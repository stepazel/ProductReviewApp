package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.graph.AggregatedRatingNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.Optional;

public interface AggregatedRatingRepositoryNeo4j extends Neo4jRepository<AggregatedRatingNeo4j, Long> {

    @Query("MATCH (p:Product {sku: $sku})--(ar:AggregatedRating) RETURN ar")
    Optional<AggregatedRatingNeo4j> findByProductSku(String sku);
}
