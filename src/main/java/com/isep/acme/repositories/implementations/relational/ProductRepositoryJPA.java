package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepositoryJPA extends CrudRepository<Product, Long> {
    List<Product> findByDesignation(String designation);

    Optional<Product> findBySku(String sku);

    //Obtain the catalog of products -> Catalog: show sku and designation of all products
    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    Optional<Product> getCatalog();

    //Obtain the catalog of products -> CatalogDetails: show sku, designation and description of all products
    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description")
    Iterable<Product> getCatalogDetails();

    //Obtain the details of a product -> Destails: show sku, designation and description of all products
    @Query("SELECT p FROM Product p WHERE p.sku=:sku AND p.description=:description AND p.designation=:designation")
    Optional<Product> getDetails(@Param("sku") String sku);

    //Delete the product when given the SKU
    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.sku=:sku")
    void deleteBySku(@Param("sku") String sku);

    //Update the product when given the SKU
    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.sku = :sku WHERE p.sku=:sku")
    Product updateBySku(@Param("sku") String sku);

    @Query("SELECT p FROM Product p WHERE p.productID=:productID")
    Optional<Product> findById(Long productID);

  /*  @Query("SELECT p FROM ProdImage p WHERE p.id=:id")
    Optional<Product> findById(Long  id); */

}
