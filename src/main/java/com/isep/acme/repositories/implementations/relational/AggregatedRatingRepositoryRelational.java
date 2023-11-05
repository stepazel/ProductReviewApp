package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.AggregatedRatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("aggregatedRatingRepositoryRelational")
public class AggregatedRatingRepositoryRelational implements AggregatedRatingRepository {
    private final AggregatedRatingRepositoryJPA aggregatedRatingRepositoryJPA;

    public AggregatedRatingRepositoryRelational(AggregatedRatingRepositoryJPA aggregatedRatingRepositoryJPA) {
        this.aggregatedRatingRepositoryJPA = aggregatedRatingRepositoryJPA;
    }

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        return aggregatedRatingRepositoryJPA.findByProductId(product);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        return aggregatedRatingRepositoryJPA.save(aggregatedRating);
    }
}
