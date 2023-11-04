package com.isep.acme.model.document;

import com.isep.acme.model.Rating;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "ratings")
public class RatingMongo {
    @Id
    private String idRating;
    private Double rate;

    public RatingMongo() {
    }

    public RatingMongo(Double rate) {
        this.rate = rate;
    }

    public Rating toDomainEntity() {
        return new Rating(rate);
    }
}
