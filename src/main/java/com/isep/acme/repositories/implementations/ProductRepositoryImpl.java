package com.isep.acme.repositories.implementations;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("productRepositoryRelational")
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductRepositoryJPA repositoryA;

    @Autowired
    public ProductRepositoryImpl(ProductRepositoryJPA productRepositoryJPA)
    {
        this.repositoryA = productRepositoryJPA;
    }

    @Override
    public List<Product> findByDesignation(String designation) {
        return this.repositoryA.findByDesignation(designation);
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return this.repositoryA.findBySku(sku);
    }

    @Override
    public Optional<Product> getCatalog() {
        return this.repositoryA.getCatalog();
    }

    @Override
    public void deleteBySku(String sku) {
        this.repositoryA.deleteBySku(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        return this.repositoryA.updateBySku(sku);
    }

    @Override
    public Optional<Product> findById(Long productID) {
        return this.repositoryA.findById(productID);
    }

    @Override
    public Iterable<Product> findAll() {
        return this.repositoryA.findAll();
    }

    @Override
    public Product save(Product product) {
        return this.repositoryA.save(product);
    }
}
