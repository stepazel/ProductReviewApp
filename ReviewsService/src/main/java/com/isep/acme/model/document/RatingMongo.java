package com.isep.acme.model.document;

import com.isep.acme.model.Rating;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ratings")
public class RatingMongo {
    @Id
    @Getter
    private Long   idRating;
    private Double rate;

    public RatingMongo() {
    }

    public RatingMongo(Long idRating, Double rate) {
        this.idRating = idRating;
        this.rate     = rate;
    }

    public RatingMongo(Double rate) {
        this.rate = rate;
    }

    public Rating toDomainEntity() {
        return new Rating(idRating, 0, rate);
    }
}
