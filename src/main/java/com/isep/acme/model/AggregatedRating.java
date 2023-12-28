package com.isep.acme.model;


import com.isep.acme.model.document.AggregatedRatingMongo;
import com.isep.acme.model.graph.AggregatedRatingNeo4j;
import com.isep.acme.model.jpa.AggregatedRatingJpa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedRating {
    private Long    aggregatedId;
    private double  average;
    private Product product;

    public AggregatedRating(double average, Product product) {
        this.average = average;
        this.product = product;
    }

    public AggregatedRatingNeo4j toGraphModel() {
        var aggregatedRating = new AggregatedRatingNeo4j(average, product);
        aggregatedRating.setId(aggregatedId);
        return aggregatedRating;
    }

    public AggregatedRatingMongo toDocumentModel() {
        return new AggregatedRatingMongo(aggregatedId, average, product);
    }

    public AggregatedRatingJpa toJpaModel() {
        return new AggregatedRatingJpa(average, product);
    }
}
