package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.Rating;
import com.isep.acme.model.graph.RatingNeo4j;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ratingRepositoryGraph")
public class RatingRepositoryGraphImpl implements RatingRepository {
    private final RatingRepositoryNeo4j ratingRepositoryNeo4j;

    public RatingRepositoryGraphImpl(RatingRepositoryNeo4j ratingRepositoryNeo4j) {
        this.ratingRepositoryNeo4j = ratingRepositoryNeo4j;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepositoryNeo4j.save(new RatingNeo4j(rating.getIdRating(), rating.getRate())).toDomainEntity();
    }

    @Override
    public Optional<Rating> findByRate(Double rate) {
        var hmm = ratingRepositoryNeo4j.findByRate(rate);
        return hmm.map(RatingNeo4j::toDomainEntity);
    }
}
