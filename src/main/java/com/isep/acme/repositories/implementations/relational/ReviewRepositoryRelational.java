package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.repositories.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("reviewRepositoryRelational")
public class ReviewRepositoryRelational implements ReviewRepository {
    private ReviewRepositoryJPA reviewRepositoryJPA;

    public ReviewRepositoryRelational(ReviewRepositoryJPA reviewRepositoryJPA) {
        this.reviewRepositoryJPA = reviewRepositoryJPA;
    }

    @Override
    public Optional<List<Review>> findByProductId(Product product) {
        return reviewRepositoryJPA.findByProductId(product);
    }

    @Override
    public Optional<List<Review>> findPendingReviews() {
        return reviewRepositoryJPA.findPendingReviews();
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        return reviewRepositoryJPA.findActiveReviews();
    }

    @Override
    public Optional<List<Review>> findByProductIdStatus(Product product, String status) {
        return reviewRepositoryJPA.findByProductIdStatus(product, status);
    }

    @Override
    public Optional<List<Review>> findByUserId(User user) {
        return reviewRepositoryJPA.findByUserId(user);
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepositoryJPA.findAll();
    }

    @Override
    public Review save(Review review) {
        return reviewRepositoryJPA.save(review);
    }

    @Override
    public Optional<Review> findById(Long reviewID) {
        return reviewRepositoryJPA.findById(reviewID);
    }

    @Override
    public void delete(Review r) {
        reviewRepositoryJPA.delete(r);
    }
}
