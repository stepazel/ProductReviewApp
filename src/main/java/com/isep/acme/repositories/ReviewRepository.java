package com.isep.acme.repositories;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository {
    Optional<List<Review>> findByProductId(Product product);

    Optional<List<Review>> findPendingReviews();

    Optional<List<Review>> findActiveReviews();

    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    Optional<List<Review>> findByUserId(User user);

    Iterable<Review> findAll();

    Review save(Review review);

    Optional<Review> findById(Long reviewID);

    void delete(Review r);
}
