package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.Product;
import com.isep.acme.model.Review;
import com.isep.acme.model.User;
import com.isep.acme.model.document.ReviewMongo;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.UniqueSequenceService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("reviewRepositoryDocument")
public class ReviewRepositoryDocumentImpl implements ReviewRepository {
    private final ReviewRepositoryMongo  reviewRepositoryMongo;
    private final UniqueSequenceService  uniqueSequenceService;
    private final ProductRepositoryMongo productRepositoryMongo;

    public ReviewRepositoryDocumentImpl(ReviewRepositoryMongo reviewRepositoryMongo,
                                        UniqueSequenceService uniqueSequenceService,
                                        ProductRepositoryMongo productRepositoryMongo) {
        this.reviewRepositoryMongo  = reviewRepositoryMongo;
        this.uniqueSequenceService  = uniqueSequenceService;
        this.productRepositoryMongo = productRepositoryMongo;
    }

    @Override
    public Optional<List<Review>> findByProductId(Product product) {
        List<ReviewMongo> reviewsMongo = reviewRepositoryMongo.findByProductId(product.getProductID());
        return Optional.of(reviewsMongo.stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findPendingReviews() {
        Optional<List<ReviewMongo>> pendingReviews = reviewRepositoryMongo.findPending();
        return pendingReviews.map(reviewMongos -> reviewMongos.stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findActiveReviews() {
        List<ReviewMongo> activeReviews = reviewRepositoryMongo.findActive();
        return Optional.of(activeReviews.stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findByProductIdStatus(Product product, String status) {
        List<ReviewMongo> reviewsMongo = reviewRepositoryMongo.findByProductIdAndStatus(product.getProductID(), status);
        return Optional.of(reviewsMongo.stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Optional<List<Review>> findByUserId(User user) {
        List<ReviewMongo> reviewsMongo = reviewRepositoryMongo.findByUserId(user.getUserId());
        return Optional.of(reviewsMongo.stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList()));
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepositoryMongo.findAll().stream().map(ReviewMongo::toDomainModel).collect(Collectors.toList());
    }

    @Override
    public Review save(Review review) {
        review.setIdReview(uniqueSequenceService.getNextSequence("review"));
        return reviewRepositoryMongo.save(review.toDocumentModel()).toDomainModel();
    }

    @Override
    public Optional<Review> findById(Long reviewID) {
        return reviewRepositoryMongo.findById(reviewID).map(ReviewMongo::toDomainModel);
    }

    @Override
    public void delete(Review review) {
        reviewRepositoryMongo.deleteById(review.getIdReview());
    }
}
