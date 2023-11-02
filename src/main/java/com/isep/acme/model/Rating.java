package com.isep.acme.model;


import com.isep.acme.model.graph.RatingNeo4j;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private Long idRating;

    private long version;

    @Getter
    @Column(nullable = false)
    private Double rate;

    protected Rating() {
    }

    public Rating(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version = Objects.requireNonNull(version);
        setRate(rate);
    }

    public Rating(Double rate) {
        setRate(rate);
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public RatingNeo4j toGraphModel() {
        return new RatingNeo4j(idRating, rate);
    }
}
