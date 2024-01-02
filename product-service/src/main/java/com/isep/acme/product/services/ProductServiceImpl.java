package com.isep.acme.product.services;

import com.isep.acme.product.model.dto.CreateProductDTO;
import com.isep.acme.product.model.Product;
import com.isep.acme.product.model.dto.ProductDTO;
import com.isep.acme.product.model.dto.ProductDetailDTO;
import com.isep.acme.product.repositories.ProductRepository;
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

        return product.map(Product::toDto);
    }


    @Override
    public Iterable<ProductDTO> findByDesignation(final String designation) {
        Iterable<Product> p = repository.findByDesignation(designation);
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDTO> getCatalog() {
        Iterable<Product> p = repository.findAll();
        List<ProductDTO> pDto = new ArrayList<>();
        for (Product pd : p) {
            pDto.add(pd.toDto());
        }

        return pDto;
    }

    @Override
    public Iterable<ProductDetailDTO> getCatalogDetails() {
        Iterable<Product> p = repository.findAll();
        List<ProductDetailDTO> pDto = new ArrayList<>();

        for (Product pd : p) {
            pDto.add(pd.toDetailDto());
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
        return repository.save(p).toDto();
    }

    @Override
    public ProductDTO updateBySku(String sku, Product product) {

        final Optional<Product> productToUpdate = repository.findBySku(sku);

        if (productToUpdate.isEmpty())
            return null;

        productToUpdate.get().updateProduct(product);

        Product productUpdated = repository.save(productToUpdate.get());

        return productUpdated.toDto();
    }

    @Override
    public void deleteBySku(String sku) {
        repository.deleteBySku(sku);
    }

    @Override
    public ProductDTO accept(String sku, String username) {
        final Optional<Product> productToUpdate = repository.findBySku(sku);
        if (productToUpdate.isEmpty())
            return null;

        var product = productToUpdate.get();
        if (product.getAcceptVoteUsername() == null || !product.getAcceptVoteUsername().equals(username)) {
            product.accept(username);
        }

        if (product.getAcceptVotesCount() >= 2) {
            product.publish();
        }

        Product productUpdated = repository.save(productToUpdate.get());

        return productUpdated.toDto();

    }
}
