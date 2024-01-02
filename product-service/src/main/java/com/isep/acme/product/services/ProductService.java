package com.isep.acme.product.services;

import com.isep.acme.product.model.Product;
import com.isep.acme.product.model.dto.CreateProductDTO;
import com.isep.acme.product.model.dto.ProductDTO;
import com.isep.acme.product.model.dto.ProductDetailDTO;

import java.util.Optional;

public interface ProductService {

    Optional<ProductDTO> findBySku(final String sku);

    Optional<Product> getProductBySku(final String sku);

    Iterable<ProductDTO> findByDesignation(final String designation);

    Iterable<ProductDTO> getCatalog();

    Iterable<ProductDetailDTO> getCatalogDetails();

    Optional<ProductDetailDTO> getDetails(final String sku);

    ProductDTO create(CreateProductDTO createProductDTO);

    ProductDTO updateBySku(final String sku, final Product product);

    void deleteBySku(final String sku);

    ProductDTO accept(String sku, String username);
}
