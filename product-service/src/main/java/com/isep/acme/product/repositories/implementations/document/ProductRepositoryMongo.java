package com.isep.acme.product.repositories.implementations.document;


import com.isep.acme.product.model.document.ProductMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryMongo extends MongoRepository<ProductMongo, String> {
    List<ProductMongo> findByDesignation(String designation);

    @Query("{'sku': ?0}")
    ProductMongo findBySku(@Param("sku") String sku);

    @Query(value = "{'sku': ?0}", delete = true)
    void deleteBySku(@Param("sku") String sku);
}
