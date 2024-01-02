package com.isep.acme.product.repositories.implementations.relational;

import com.isep.acme.product.model.Product;
import com.isep.acme.product.model.jpa.ProductJpa;
import com.isep.acme.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component("productRepositoryRelational")
public class ProductRepositoryRelational implements ProductRepository {

    private final ProductRepositoryJPA repositoryA;

    @Autowired
    public ProductRepositoryRelational(ProductRepositoryJPA productRepositoryJPA) {
        this.repositoryA = productRepositoryJPA;
    }

    @Override
    public List<Product> findByDesignation(String designation) {
        return this.repositoryA.findByDesignation(designation).stream().map(ProductJpa::toDomainEntity).collect(java.util.stream.Collectors.toList());
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return this.repositoryA.findBySku(sku).map(ProductJpa::toDomainEntity);
    }

    @Override
    public Optional<Product> getCatalog() {
        return this.repositoryA.getCatalog().map(ProductJpa::toDomainEntity);
    }

    @Override
    public Optional<Product> getDetails(String sku) {
        return this.repositoryA.getDetails(sku).map(ProductJpa::toDomainEntity);
    }

    @Override
    public Iterable<Product> getCatalogDetails() {
        return StreamSupport.stream(this.repositoryA.getCatalogDetails().spliterator(), false)
                            .map(ProductJpa::toDomainEntity)
                            .collect(Collectors.toList());
    }

    @Override
    public void deleteBySku(String sku) {
        this.repositoryA.deleteBySku(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        return this.repositoryA.updateBySku(sku).toDomainEntity();
    }

    @Override
    public Optional<Product> findById(Long productID) {
        return this.repositoryA.findById(productID).map(ProductJpa::toDomainEntity);
    }

    @Override
    public Iterable<Product> findAll() {
        return StreamSupport.stream(this.repositoryA.findAll().spliterator(), false)
                            .map(ProductJpa::toDomainEntity)
                            .collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        var jpaProduct = new ProductJpa(product.getSku(), product.getDesignation(), product.getDescription(), product.isPublished(), product.getAcceptVotesCount(), product.getAcceptVoteUsername());
        return this.repositoryA.save(jpaProduct).toDomainEntity();
    }
}
