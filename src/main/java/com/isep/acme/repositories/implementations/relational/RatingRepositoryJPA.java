package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.Rating;
import com.isep.acme.model.jpa.RatingJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RatingRepositoryJPA extends CrudRepository<RatingJpa, Long> {
    @Query("SELECT r FROM RatingJpa r WHERE r.rate=:rate")
    Optional<Rating> findByRate(Double rate);
}
