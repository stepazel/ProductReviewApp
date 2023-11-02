package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.graph.ProductNeo4j;
import com.isep.acme.model.graph.ReviewNeo4j;
import com.isep.acme.model.graph.UserNeo4j;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryNeo4j extends Neo4jRepository<ReviewNeo4j, Long> {

    @Query("MATCH (p:Product {sku: $sku})-[:REVIEWED]->(r:Review) RETURN r")
    Optional<List<ReviewNeo4j>> findByProduct(@Param("sku") String productSku);

    Optional<ReviewNeo4j> findById(Long id);

    @Query("MATCH (u:User {username: $userName})-[:VOTED]->(r:Review) RETURN r, u")
    Optional<List<ReviewNeo4j>> findByUser(@Param("userName") String userName);

    @Query("MATCH (r:Review)-[:REVIEWED]->(p:Product) WHERE r.approvalStatus = \"pending\" RETURN r, p")
    Optional<List<ReviewNeo4j>> findPending();

    @Query("MATCH (p:Product {sku: $sku})<-[:REVIEWED]-(r:Review) WHERE r.approvalStatus = $approvalStatus RETURN r,p")
    List<ReviewNeo4j> findByProductSkuAndStatus(@Param("sku") String sku, @Param("approvalStatus") String status);
}
