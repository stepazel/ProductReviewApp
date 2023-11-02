package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.repositories.AggregatedRatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("aggregatedRatingRepositoryGraph")
public class AggregatedRatingRepositoryGraphImpl implements AggregatedRatingRepository {
    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        return Optional.empty();
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        return null;
    }
}
