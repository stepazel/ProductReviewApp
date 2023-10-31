package com.isep.acme.model;


import com.isep.acme.model.document.AggregatedRatingMongo;
import com.isep.acme.model.graph.AggregatedRatingNeo4j;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class AggregatedRating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggregatedId;

    @Column()
    private double average;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Product product;

    protected AggregatedRating() {
    }

    public AggregatedRating(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public AggregatedRatingNeo4j toGraphModel() {
        var aggregatedRating = new AggregatedRatingNeo4j(average, product);
        aggregatedRating.setId(aggregatedId);
        return aggregatedRating;
    }

    public AggregatedRatingMongo toDocumentModel() {
        return new AggregatedRatingMongo(average, product);
    }

    public void setAggregatedId(Long aggregatedId) {
        this.aggregatedId = aggregatedId;
    }
}
