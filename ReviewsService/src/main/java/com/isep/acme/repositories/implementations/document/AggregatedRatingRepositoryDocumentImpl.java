package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.model.document.AggregatedRatingMongo;
import com.isep.acme.repositories.AggregatedRatingRepository;
import com.isep.acme.services.UniqueSequenceService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("aggregatedRatingRepositoryDocument")
public class AggregatedRatingRepositoryDocumentImpl implements AggregatedRatingRepository {
    private final AggregatedRatingRepositoryMongo aggregatedRatingRepositoryMongo;
    private final UniqueSequenceService           uniqueSequenceService;

    public AggregatedRatingRepositoryDocumentImpl(AggregatedRatingRepositoryMongo aggregatedRatingRepositoryMongo,
                                                  UniqueSequenceService uniqueSequenceService) {
        this.aggregatedRatingRepositoryMongo = aggregatedRatingRepositoryMongo;
        this.uniqueSequenceService           = uniqueSequenceService;
    }

    @Override
    public Optional<AggregatedRating> findByProductId(Product product) {
        var aggregatedRating = aggregatedRatingRepositoryMongo.findByProductSku(product.getSku());
        return aggregatedRating.map(AggregatedRatingMongo::toDomainEntity);
    }

    @Override
    public AggregatedRating save(AggregatedRating aggregatedRating) {
        aggregatedRating.setAggregatedId(uniqueSequenceService.getNextSequence("aggregated_rating"));
        return aggregatedRatingRepositoryMongo.save(aggregatedRating.toDocumentModel()).toDomainEntity();
    }
}
