package com.isep.acme.product.repositories;

import com.isep.acme.product.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    Optional<Product> getCatalog();

    Iterable<Product> getCatalogDetails();

    Optional<Product> getDetails(@Param("sku") String sku);

    void deleteBySku(@Param("sku") String sku);

    Product updateBySku(@Param("sku") String sku);

    Optional<Product> findById(Long productID);

    Iterable<Product> findAll();

    Product save(Product product);
}

