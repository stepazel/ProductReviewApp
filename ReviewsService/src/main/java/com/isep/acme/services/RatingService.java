package com.isep.acme.services;

import com.isep.acme.model.Rating;

import java.util.Optional;

public interface RatingService {

    Optional<Rating> findByRate(Double rate);
}
