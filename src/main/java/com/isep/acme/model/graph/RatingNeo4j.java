package com.isep.acme.model.graph;

import com.isep.acme.model.Rating;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Rating")
public class RatingNeo4j {
    @Id
    @GeneratedValue
    private Long idRating;

    @Property
    private Double rate;

    public RatingNeo4j(Long idRating, Double rate) {
        this.idRating = idRating;
        this.rate     = rate;
    }

    public RatingNeo4j() {}

    public RatingNeo4j(Double rate) {
        this.rate = rate;
    }

    public Rating toDomainEntity() {
        return new Rating(idRating, 0, rate);
    }
}
