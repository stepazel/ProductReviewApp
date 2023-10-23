package com.isep.acme.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.isep.acme.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    Optional<Product> getCatalog();

    void deleteBySku(@Param("sku") String sku);

    Product updateBySku(@Param("sku") String sku);

    Optional<Product> findById(Long productID);

    Iterable<Product> findAll();

    Product save(Product product);
}

