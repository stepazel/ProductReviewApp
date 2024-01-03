package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.document.AggregatedRatingMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface AggregatedRatingRepositoryMongo extends MongoRepository<AggregatedRatingMongo, Long> {

    @Query("{'product.sku': ?0}")
    Optional<AggregatedRatingMongo> findByProductSku(String sku);
}
