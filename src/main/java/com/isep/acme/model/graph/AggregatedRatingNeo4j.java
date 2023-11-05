package com.isep.acme.model.graph;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;


@Getter
@Node("AggregatedRating")
public class AggregatedRatingNeo4j {
    @Id
    @GeneratedValue
    private Long id;

    @Property
    private double average;

    @Relationship(type = "HAS")
    private ProductNeo4j product;

    protected AggregatedRatingNeo4j() {
    }

    public AggregatedRatingNeo4j(double average, Product product) {
        this.average = average;
        this.product = product.toGraphModel();
    }

    public AggregatedRating toDomainEntity() {
        var ar = new AggregatedRating(average, product.toDomainEntity());
        ar.setAggregatedId(id);
        return ar;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
