package com.isep.acme.tests.api;


import com.isep.acme.model.CreateReviewDTO;
import com.isep.acme.model.ReviewDTO;
import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@SpringBootTest
public class ReviewTests {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final RestTemplate restTemplate;

    @Autowired
    public ReviewTests(UserRepository userRepository, @Qualifier("productRepositoryRelational") ProductRepository productRepository, RestTemplateBuilder restTemplateBuilder) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.restTemplate = restTemplateBuilder.build();
    }

    @Test
    public void createReviewTest() {
        var user = userRepository.findByUsername("admin1@mail.com");
        var product = productRepository.findBySku("c1d4e7r8d5f2");

        if (user.isEmpty()) {
            Assertions.fail("User data is not set up properly");
        }
        var createReviewDTO = new CreateReviewDTO("apiTest");
        createReviewDTO.setRating(1.0);
        createReviewDTO.setUserID(user.get().getUserId());

        var response = restTemplate.postForEntity("http://localhost:8080/products/" + product.get().sku + "/reviews", createReviewDTO, ReviewDTO.class);

        var createdReview = response.getBody();

        assert createdReview != null;
        assert Objects.equals(createdReview.getReviewText(), createReviewDTO.getReviewText());
        assert Objects.equals(createdReview.getApprovalStatus(), "pending");
        assert Objects.equals(createdReview.getRating(), createReviewDTO.getRating());
    }


}

