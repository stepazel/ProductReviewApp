package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.Rating;
import com.isep.acme.model.document.RatingMongo;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ratingRepositoryDocument")
public class RatingRepositoryDocumentImpl implements RatingRepository {
    private final RatingRepositoryMongo ratingRepositoryMongo;

    public RatingRepositoryDocumentImpl(RatingRepositoryMongo ratingRepositoryMongo) {
        this.ratingRepositoryMongo = ratingRepositoryMongo;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepositoryMongo.save(new RatingMongo(rating.getRate())).toDomainEntity();
    }

    @Override
    public Optional<Rating> findByRate(Double rate) {
        var hmm = ratingRepositoryMongo.findByRate(rate);
        return hmm.map(RatingMongo::toDomainEntity);
    }
}

