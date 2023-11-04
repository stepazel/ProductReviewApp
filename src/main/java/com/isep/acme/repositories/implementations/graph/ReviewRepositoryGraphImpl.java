package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.model.graph.ReviewNeo4j;
import com.isep.acme.repositories.ReviewRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("reviewRepositoryGraph")
public class ReviewRepositoryGraphImpl implements ReviewRepository {
    private final ReviewRepositoryNeo4j reviewRepositoryNeo4j;

    private final ProductRepositoryNeo4j productRepositoryNeo4j;

    public ReviewRepositoryGraphImpl(ReviewRepositoryNeo4j reviewRepositoryNeo4j,
                                     ProductRepositoryNeo4j productRepositoryNeo4j) {
        this.reviewRepositoryNeo4j  = reviewRepositoryNeo4j;
        this.productRepositoryNeo4j = productRepositoryNeo4j;
    }

    @Override
    public Optional<List<Review>> findByProductId(Product product) {
        var reviews = reviewRepositoryNeo4j.findByProduct(product.getSku());
        return Optional.of(reviews.get().stream().map(reviewNeo4js -> reviewRepositoryNeo4j.findById(reviewNeo4js.getId()).get().toDomainModel()).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findPendingReviews() {
        Optional<List<ReviewNeo4j>> pendingReviews = reviewRepositoryNeo4j.findPending();
        if (pendingReviews.isEmpty())
            return Optional.empty();

        return Optional.of(pendingReviews.get().stream().map(reviewNeo4j -> reviewRepositoryNeo4j.findById(reviewNeo4j.getId()).get().toDomainModel()).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        return Optional.empty();
    }

    @Override
    public Optional<List<Review>> findByProductIdStatus(Product product, String status) {
        List<ReviewNeo4j> byProductSkuAndStatus = reviewRepositoryNeo4j.findByProductSkuAndStatus(product.sku, status);
        //        return byProductSkuAndStatus.map(reviewNeo4js -> reviewNeo4js.stream().map
        //        (ReviewNeo4j::toDomainModel).collect(Collectors.toList()));
        //        return Optional.of(byProductSkuAndStatus.stream().map(ReviewNeo4j::toDomainModel).collect
        //        (Collectors.toList()));
        return Optional.of(byProductSkuAndStatus.stream().map(reviewNeo4j -> reviewRepositoryNeo4j.findById(reviewNeo4j.getId()).get().toDomainModel()).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findByUserId(User user) {
        var reviews = reviewRepositoryNeo4j.findByUser(user.getUsername()).get();
        return Optional.of(reviews.stream().map(reviewNeo4j -> reviewRepositoryNeo4j.findById(reviewNeo4j.getId()).get().toDomainModel()).collect(Collectors.toList()));
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepositoryNeo4j.findAll().stream().map(ReviewNeo4j::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Review save(Review review) {
        return reviewRepositoryNeo4j.save(review.toGraphModel()).toDomainModel();
    }

    @Override
    public Optional<Review> findById(Long reviewID) {
        return reviewRepositoryNeo4j.findById(reviewID).map(ReviewNeo4j::toDomainModel);
    }

    @Override
    public void delete(Review r) {
        reviewRepositoryNeo4j.deleteById(r.getIdReview());
    }
}
