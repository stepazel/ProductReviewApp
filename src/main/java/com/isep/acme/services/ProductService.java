package com.isep.acme.services;

import com.isep.acme.model.Product;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<Product> getProductBySku(final String sku);

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    Iterable<ProductDetailDTO> getCatalogDetails();

    Optional<ProductDetailDTO> getDetails(final String sku);

    ProductDTO create(final Product manager);

    ProductDTO updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);
}
