package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.document.RatingMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RatingRepositoryMongo extends MongoRepository<RatingMongo, Long> {
    @Query("{'rate': ?0}")
    Optional<RatingMongo> findByRate(@Param("rate") Double rate);
}
