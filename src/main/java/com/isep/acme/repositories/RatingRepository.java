package com.isep.acme.repositories;

import com.isep.acme.model.Rating;

import java.util.Optional;

public interface RatingRepository {
    Rating save(Rating rating);

    Optional<Rating> findByRate(Double rate);
}
