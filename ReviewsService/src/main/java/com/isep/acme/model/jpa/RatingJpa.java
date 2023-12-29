package com.isep.acme.model.jpa;


import com.isep.acme.model.Rating;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class RatingJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long idRating;

    private long version;

    @Setter
    @Getter
    @Column(nullable = false)
    private Double rate;

    public RatingJpa() {
    }

    public RatingJpa(Long idRating, long version, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        this.version  = version;
        setRate(rate);
    }

    public RatingJpa(Long idRating, Double rate) {
        this.idRating = Objects.requireNonNull(idRating);
        setRate(rate);
    }

    public RatingJpa(Double rate) {
        setRate(rate);
    }

    public Rating toDomainEntity() {
        return new Rating(rate);
    }
}
