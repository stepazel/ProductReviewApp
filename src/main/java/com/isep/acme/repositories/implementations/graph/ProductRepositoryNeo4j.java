package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.Product;
import com.isep.acme.model.graph.ProductNeo4j;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;


public interface ProductRepositoryNeo4j extends Neo4jRepository<ProductNeo4j, String> {
    List<ProductNeo4j> findByDesignation(String designation);

    @Query("CREATE (p:Product) SET p = $product RETURN p")
    ProductNeo4j saveProduct(@Param("product") Product product);

    @Query("MATCH (p:Product {sku: $sku}) SET p = $product RETURN p")
    ProductNeo4j updateProduct(@Param("sku") Long id, @Param("product") Product product);
}
