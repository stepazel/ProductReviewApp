package com.isep.acme.product.repositories.implementations.document;

import com.isep.acme.product.model.Product;
import com.isep.acme.product.model.document.ProductMongo;
import com.isep.acme.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("productRepositoryDocument")
public class ProductRepositoryDocumentImpl implements ProductRepository {
    private final ProductRepositoryMongo productRepositoryMongo;

    @Autowired
    public ProductRepositoryDocumentImpl(ProductRepositoryMongo productRepositoryMongo) {
        this.productRepositoryMongo = productRepositoryMongo;
    }

    @Override
    public List<Product> findByDesignation(String designation) {
        return productRepositoryMongo.findByDesignation(designation).stream().map(ProductMongo::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepositoryMongo.findById(sku).map(ProductMongo::toDomainEntity);
    }

    @Override
    public void deleteBySku(String sku) {
        productRepositoryMongo.deleteById(sku);
    }


    @Override
    public Iterable<Product> findAll() {
        return productRepositoryMongo.findAll().stream().map(ProductMongo::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        var mongoProduct = new ProductMongo(product.getSku(), product.getDesignation(), product.getDescription());
        return productRepositoryMongo.save(mongoProduct).toDomainEntity();
    }

    @Override
    public Optional<Product> getCatalog() {
        return productRepositoryMongo.findAll().stream().map(ProductMongo::toDomainEntity).findFirst();
    }

    @Override
    public Iterable<Product> getCatalogDetails() {
        return productRepositoryMongo.findAll().stream().map(ProductMongo::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> getDetails(String sku) {
        return productRepositoryMongo.findById(sku).map(ProductMongo::toDomainEntity);
    }

    @Override
    public Product updateBySku(String sku) {
        // not implemented
        return new Product(sku);
    }


    @Override
    public Optional<Product> findById(Long productID) {
        // not implemented
        return Optional.empty();
    }
}
