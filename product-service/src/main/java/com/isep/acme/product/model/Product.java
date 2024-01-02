package com.isep.acme.product.model;

import com.isep.acme.product.model.document.ProductMongo;
import com.isep.acme.product.model.dto.ProductDTO;
import com.isep.acme.product.model.dto.ProductDetailDTO;
import com.isep.acme.product.model.graph.ProductNeo4j;
import com.isep.acme.product.model.jpa.ProductJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;


@Getter
@NoArgsConstructor
public class Product {

    public  String sku;
    private Long   productID;
    private String designation;
    private String description;
    /*
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    public Product(final Long productID, final String sku) {
        this.productID = Objects.requireNonNull(productID);
        setSku(sku);
    }

    public Product(final Long productID, final String sku, final String designation, final String description) {
        this(productID, sku);
        setDescription(description);
        setDesignation(designation);
    }

    public Product(final String sku) {
        setSku(sku);
    }

    public Product(final String sku, final String designation, final String description) {
        this(sku);
        setDescription(description);
        setDesignation(designation);
    }

    public void setDesignation(String designation) {
        if (designation == null || designation.isBlank()) {
            throw new IllegalArgumentException("Designation is a mandatory attribute of Product.");
        }
        if (designation.length() > 50) {
            throw new IllegalArgumentException("Designation must not be greater than 50 characters.");
        }
        this.designation = designation;
    }

    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description is a mandatory attribute of Product.");
        }

        if (description.length() > 1200) {
            throw new IllegalArgumentException("Description must not be greater than 1200 characters.");
        }

        this.description = description;
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU is a mandatory attribute of Product.");
        }
        if (sku.length() != 12) {
            throw new IllegalArgumentException("SKU must be 12 characters long.");
        }

        this.sku = sku;
    }

    public void updateProduct(Product p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public ProductDTO toDto() {
        return new ProductDTO(this.sku, this.designation);
    }

    public ProductNeo4j toGraphModel() {
        return new ProductNeo4j(getSku(), getDesignation(), getDescription());
    }

    public ProductMongo toDocumentModel() {
        return new ProductMongo(getSku(), getDesignation(), getDescription());
    }

    public ProductJpa toJpaModel() {
        return new ProductJpa(getSku(), getDesignation(), getDescription());
    }
    
    public ProductDetailDTO toDetailDto() {
        return new ProductDetailDTO(getSku(), getDesignation(), getDescription());
    }

/*
    public List<Review> getReview() {
        return review;
    }

    public void setReview(List<Review> review) {
        this.review = review;
    }
*/

}
