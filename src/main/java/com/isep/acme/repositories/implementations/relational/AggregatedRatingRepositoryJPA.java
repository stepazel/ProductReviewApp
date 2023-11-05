package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AggregatedRatingRepositoryJPA extends CrudRepository<AggregatedRating, Long> {

    @Query("SELECT a FROM AggregatedRating a WHERE a.product=:product")
    Optional<AggregatedRating> findByProductId(Product product);

}
