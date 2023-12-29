package com.isep.acme.services;

import java.util.Optional;

import com.isep.acme.model.dto.CreateProductDTO;
import com.isep.acme.model.Product;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;

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
}
