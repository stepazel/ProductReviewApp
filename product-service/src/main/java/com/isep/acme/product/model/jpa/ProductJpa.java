package com.isep.acme.product.model.jpa;

import com.isep.acme.product.model.Product;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
public class ProductJpa {


    @Column(nullable = false, unique = true)
    public  String sku;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long   productID;
    @Column(nullable = false)
    private String designation;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean isPublished;

    @Column(nullable = false)
    private int acceptVotesCount;

    @Column(nullable = false)
    private String acceptVoteUsername;

    public ProductJpa() {
    }

//    public ProductJpa(final Long productID, final String sku) {
//        this.productID = Objects.requireNonNull(productID);
//        setSku(sku);
//    }

//    public ProductJpa(final Long productID, final String sku, final String designation, final String description) {
//        this(productID, sku);
//        setDescription(description);
//        setDesignation(designation);
//    }

    public ProductJpa(final String sku) {
        setSku(sku);
    }

    public ProductJpa(final String sku, final String designation, final String description, boolean isPublished, int acceptVotesCount, String acceptVoteUsername) {
        this(sku);
        setDescription(description);
        setDesignation(designation);

        this.isPublished = isPublished;
        this.acceptVotesCount = acceptVotesCount;
        this.acceptVoteUsername = acceptVoteUsername;
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

    public void updateProduct(ProductJpa p) {
        setDesignation(p.designation);
        setDescription(p.description);
    }

    public Product toDomainEntity() {
        return new Product(productID, sku, designation, description);
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
