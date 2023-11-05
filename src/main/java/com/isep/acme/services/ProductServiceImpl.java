package com.isep.acme.services;

import com.isep.acme.mappers.ProductMapper;
import com.isep.acme.model.dto.CreateProductDTO;
import com.isep.acme.model.Product;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;
import com.isep.acme.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final SKUGenerator skuGenerator;

    public ProductServiceImpl(@Qualifier("ProductRepositoryAlias") ProductRepository repository, @Qualifier(
            "SKUGeneratorAlias") SKUGenerator skuGenerator) {
        this.repository   = repository;
        this.skuGenerator = skuGenerator;
    }

    @Override
    public Optional<Product> getProductBySku(final String sku) {

        return repository.findBySku(sku);
    }

    @Override
    public Optional<ProductDTO> findBySku(String sku) {
        final Optional<Product> product = repository.findBySku(sku);

        return product.map(ProductMapper::toDto);
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(ProductMapper.toDto(pd));
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(ProductMapper.toDto(pd));
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDetailDTO> getCatalogDetails() {
        Iterable<Product> p = repository.findAll();
        List<ProductDetailDTO> pDto = new ArrayList<>();

        for (Product pd : p) {
            pDto.add(ProductMapper.toDetailDto(pd));
        }

        return pDto;
    }

    public Optional<ProductDetailDTO> getDetails(String sku) {

        Optional<Product> p = repository.findBySku(sku);

        return p.map(product -> Optional.of(new ProductDetailDTO(product.getSku(), product.getDesignation(),
                product.getDescription()))).orElse(null);
    }


    @Override
    public ProductDTO create(final CreateProductDTO createProductDTO) {
        final Product p = new Product(skuGenerator.generateNew(createProductDTO.getDesignation()),
                createProductDTO.getDesignation(), createProductDTO.getDescription());
        return ProductMapper.toDto(repository.save(p));
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {

        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if (productToUpdate.isEmpty())
            return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = repository.save(productToUpdate.get());

        return ProductMapper.toDto(productUpdated);
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }
}
