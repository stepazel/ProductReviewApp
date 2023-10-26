package com.isep.acme.repositories.implementations;

import com.isep.acme.model.Product;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("productRepositoryDocument")
public class ProductRepositoryDocumentImpl implements ProductRepository {
    @Override
    public List<Product> findByDesignation(String designation) {
        return null;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return Optional.empty();
    }

    @Override
    public Optional<Product> getCatalog() {
        return Optional.empty();
    }

    @Override
    public void deleteBySku(String sku) {

    }

    @Override
    public Product updateBySku(String sku) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long productID) {
        return Optional.empty();
    }

    @Override
    public Iterable<Product> findAll() {
        return null;
    }

    @Override
    public Product save(Product product) {
        return null;
    }
}
