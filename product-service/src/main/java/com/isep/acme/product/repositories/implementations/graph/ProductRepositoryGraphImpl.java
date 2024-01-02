package com.isep.acme.product.repositories.implementations.graph;

import com.isep.acme.product.model.Product;
import com.isep.acme.product.model.graph.ProductNeo4j;
import com.isep.acme.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("productRepositoryGraph")
public class ProductRepositoryGraphImpl implements ProductRepository {
    private final ProductRepositoryNeo4j productRepositoryNeo4j;

    @Autowired
    public ProductRepositoryGraphImpl(ProductRepositoryNeo4j productRepositoryNeo4j) {
        this.productRepositoryNeo4j = productRepositoryNeo4j;
    }

    @Override
    public List<Product> findByDesignation(String designation) {
        return productRepositoryNeo4j.findByDesignation(designation).stream().map(ProductNeo4j::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepositoryNeo4j.findById(sku).map(ProductNeo4j::toDomainEntity);
    }

    @Override
    public Optional<Product> getCatalog() {
        // it is not used
        return Optional.empty();
    }

    @Override
    public Iterable<Product> getCatalogDetails() {
        return null;
    }

    @Override
    public Optional<Product> getDetails(String sku) {
        // it is not used
        return Optional.empty();
    }

    @Override
    public void deleteBySku(String sku) {
        productRepositoryNeo4j.deleteById(sku);
    }

    @Override
    public Product updateBySku(String sku) {
        // ignore this.. it is not used
        return new Product(sku);
        //        return productRepositoryGraph.updateProduct();
    }

    @Override
    public Optional<Product> findById(Long productID) {
        // only used in the FileController - refactor
        return Optional.empty();
    }

    @Override
    public Iterable<Product> findAll() {
        return productRepositoryNeo4j.findAll().stream().map(ProductNeo4j::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Product save(Product product) {
        var neo4jProduct = new ProductNeo4j(product.sku, product.getDesignation(), product.getDescription());
        return productRepositoryNeo4j.save(neo4jProduct).toDomainEntity();
    }
}
