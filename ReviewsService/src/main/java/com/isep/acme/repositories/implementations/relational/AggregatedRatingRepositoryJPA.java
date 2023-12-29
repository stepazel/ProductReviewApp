package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;
import com.isep.acme.model.jpa.AggregatedRatingJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AggregatedRatingRepositoryJPA extends CrudRepository<AggregatedRatingJpa, Long> {

    @Query("SELECT a FROM AggregatedRatingJpa a WHERE a.product=:product")
    Optional<AggregatedRating> findByProductId(Product product);

}
