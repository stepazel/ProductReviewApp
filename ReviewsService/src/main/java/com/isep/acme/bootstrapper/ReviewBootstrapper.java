package com.isep.acme.bootstrapper;

import com.isep.acme.model.Review;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.RatingRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ReviewBootstrapper implements CommandLineRunner, Ordered {
    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final RatingRepository ratingRepository;

    public ReviewBootstrapper(@Qualifier("ReviewRepositoryAlias") ReviewRepository reviewRepository, @Qualifier(
            "ProductRepositoryAlias") ProductRepository productRepository,
                              @Qualifier("UserRepositoryAlias") UserRepository userRepository, @Qualifier(
                                      "RatingRepositoryAlias") RatingRepository ratingRepository) {
        this.reviewRepository  = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository    = userRepository;
        this.ratingRepository  = ratingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
//        if (reviewRepository.findAll().iterator().hasNext()) {
        if (true) {
            return;
        }

        var users = StreamSupport.stream(userRepository.findAll().spliterator(), false).collect(Collectors.toList());
        var products = StreamSupport.stream(productRepository.findAll().spliterator(), false).collect(Collectors.toList());
        var ratings = StreamSupport.stream(ratingRepository.findAll().spliterator(), false).collect(Collectors.toList());

        var random = new Random();
        for (var i = 0; i < 100; i++) {
            var user = users.get(random.nextInt(users.size()));
            var product = products.get(random.nextInt(products.size()));
            var rating = ratings.get(random.nextInt(ratings.size()));
            var review = new Review(
                    "Test review " + i,
                    LocalDate.now(),
                    product,
                    "this is created automatically",
                    rating,
                    user);
            review.setApprovalStatus("approved");

            var reviewer = users.get(random.nextInt(users.size()));
            for (var j = 0; j < 10; j++) {
                if (i % 2 == 0) {
                    review.addUpVote(new Vote("upVote", reviewer.getUserId()));
                } else {
                    review.addDownVote(new Vote("downVote", reviewer.getUserId()));
                }
            }

            reviewRepository.save(review);
        }
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
