package com.isep.acme.model;


import com.isep.acme.model.document.RatingMongo;
import com.isep.acme.model.graph.RatingNeo4j;
import com.isep.acme.model.jpa.RatingJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Rating {
    private Long   idRating;
    private long   version;
    private Double rate;

    public Rating(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version  = version;
        setRate(rate);
    }

    public Rating(Double rate) {
        setRate(rate);
    }

    public RatingNeo4j toGraphModel() {
        return new RatingNeo4j(idRating, rate);
    }

    public RatingMongo toDocumentModel() {
        return new RatingMongo(idRating, rate);
    }
    
    public RatingJpa toJpaModel() {
        return new RatingJpa(idRating, rate);
    }
}
