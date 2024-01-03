package com.isep.acme.model.document;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "aggregatedRatings")
public class AggregatedRatingMongo {

    @Id
    private Long id;

    private double average;

    @DBRef
    private ProductMongo product;

    public AggregatedRatingMongo() {
    }

    public AggregatedRatingMongo(Long id, double average, Product product) {
        this.id      = id;
        this.average = average;
        this.product = product.toDocumentModel();
    }

    public AggregatedRating toDomainEntity() {
        var ar = new AggregatedRating(average, product.toDomainEntity());
        ar.setAggregatedId(id);
        return ar;
    }
}
