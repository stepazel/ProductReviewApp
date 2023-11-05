package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.Rating;
import com.isep.acme.model.document.RatingMongo;
import com.isep.acme.repositories.RatingRepository;
import com.isep.acme.services.UniqueSequenceService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("ratingRepositoryDocument")
public class RatingRepositoryDocumentImpl implements RatingRepository {
    private final RatingRepositoryMongo ratingRepositoryMongo;
    private final UniqueSequenceService uniqueSequenceService;

    public RatingRepositoryDocumentImpl(RatingRepositoryMongo ratingRepositoryMongo,
                                        UniqueSequenceService uniqueSequenceService) {
        this.ratingRepositoryMongo = ratingRepositoryMongo;
        this.uniqueSequenceService = uniqueSequenceService;
    }

    @Override
    public Rating save(Rating rating) {
        rating.setIdRating(uniqueSequenceService.getNextSequence("rating"));
        return ratingRepositoryMongo.save(rating.toDocumentModel()).toDomainEntity();
    }

    @Override
    public Optional<Rating> findByRate(Double rate) {
        var hmm = ratingRepositoryMongo.findByRate(rate);
        return hmm.map(RatingMongo::toDomainEntity);
    }
}

