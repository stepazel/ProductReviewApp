package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.Rating;
import com.isep.acme.model.jpa.RatingJpa;
import com.isep.acme.repositories.RatingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Component("ratingRepositoryRelational")
public class RatingRepositoryRelational implements RatingRepository {
    private final RatingRepositoryJPA ratingRepositoryJPA;

    public RatingRepositoryRelational(RatingRepositoryJPA ratingRepositoryJPA) {
        this.ratingRepositoryJPA = ratingRepositoryJPA;
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepositoryJPA.save(new RatingJpa(rating.getRate())).toDomainEntity();
    }

    @Override
    public Optional<Rating> findByRate(Double rate) {
        return ratingRepositoryJPA.findByRate(rate);
    }

    @Override
    public Iterable<Rating> findAll() {
        return StreamSupport.stream(ratingRepositoryJPA.findAll().spliterator(), false)
                            .map(RatingJpa::toDomainEntity)
                            .collect(java.util.stream.Collectors.toList());
    }
}
