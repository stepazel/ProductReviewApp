package com.isep.acme.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.Product;

import java.util.Optional;

public interface AggregatedRatingRepository {

    Optional<AggregatedRating> findByProductId(Product product);

    AggregatedRating save(AggregatedRating aggregatedRating);

}
