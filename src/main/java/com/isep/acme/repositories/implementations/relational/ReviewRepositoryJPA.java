package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.model.jpa.ReviewJpa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepositoryJPA extends CrudRepository<ReviewJpa, Long> {
    @Query("SELECT r FROM ReviewJpa r WHERE r.product=:product ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByProductId(Product product);

    @Query("SELECT r FROM ReviewJpa r WHERE r.approvalStatus='pending'")
    Optional<List<Review>> findPendingReviews();

    @Query("SELECT r FROM ReviewJpa r WHERE r.approvalStatus='active'")
    Optional<List<Review>> findActiveReviews();

    @Query("SELECT r FROM ReviewJpa r WHERE r.product=:product AND r.approvalStatus=:status ORDER BY r.publishingDate " + "DESC")
    Optional<List<Review>> findByProductIdStatus(Product product, String status);

    @Query("SELECT r FROM ReviewJpa r WHERE r.user=:user ORDER BY r.publishingDate DESC")
    Optional<List<Review>> findByUserId(User user);
}
