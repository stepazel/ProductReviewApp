package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.model.graph.AggregatedRatingNeo4j;
import com.isep.acme.repositories.AggregatedRatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("aggregatedRatingRepositoryGraph")
public class AggregatedRatingRepositoryGraphImpl implements AggregatedRatingRepository {
    private final AggregatedRatingRepositoryNeo4j aggregatedRatingRepositoryNeo4j;

    public AggregatedRatingRepositoryGraphImpl(AggregatedRatingRepositoryNeo4j aggregatedRatingRepositoryNeo4j) {
        this.aggregatedRatingRepositoryNeo4j = aggregatedRatingRepositoryNeo4j;
    }

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        var aggregatedRating = aggregatedRatingRepositoryNeo4j.findByProductSku(product.sku);
        if (aggregatedRating.isEmpty()) {
            return Optional.empty();
        }
        var completeAr = aggregatedRatingRepositoryNeo4j.findById(aggregatedRating.get().getId());
        return completeAr.map(AggregatedRatingNeo4j::toDomainEntity);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        return aggregatedRatingRepositoryNeo4j.save(aggregatedRating.toGraphModel()).toDomainEntity();
    }
}
