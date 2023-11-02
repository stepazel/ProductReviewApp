package com.isep.acme.bootstrapper;

import com.isep.acme.model.Review;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.RatingRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReviewBootstrapper implements CommandLineRunner, Ordered {
    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final RatingRepository ratingRepository;

    public ReviewBootstrapper(@Qualifier("ReviewRepositoryAlias") ReviewRepository reviewRepository,
                              @Qualifier("ProductRepositoryAlias") ProductRepository productRepository,
                              @Qualifier("UserRepositoryAlias") UserRepository userRepository,
                              @Qualifier("RatingRepositoryAlias") RatingRepository ratingRepository) {
        this.reviewRepository = reviewRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        var user = userRepository.findByUsername("admin1@mail.com").get();
        var product = productRepository.findBySku("vgb576hgb675").get();
        var rating = ratingRepository.findByRate(1.0).get();
        var review = new Review("very good review", LocalDate.now(), product, "this is created automatically", rating, user);
//        var createdReview = reviewRepository.save(review);
    }

    @Override
    public int getOrder() {
        return 4;
    }
}
