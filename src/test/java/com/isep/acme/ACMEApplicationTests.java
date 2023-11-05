package com.isep.acme;

import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.model.*;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.FirstRecommendationServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ACMEApplicationTests {
    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    void test() {
        Mockito.when(reviewRepository.findAll()).thenReturn(List.of(getReview()));

        var recommendationService = new FirstRecommendationServiceImpl(reviewRepository);

        Assertions.assertEquals(recommendationService.getReviewRecommendedForUser(21L).get(0).getIdReview(), ReviewMapper.toDto(getReview()).getIdReview());
    }

    private Review getReview() {
        return new Review(
                3L, 0, "ephemeral", "blabla",
                getVotes(4, true),
                getVotes(0, false),
                "report",
                LocalDate.now(),
                "funFact",
                new Product(),
                new Rating(),
                new User());
    }

    private List<Vote> getVotes(int numberOfVotes, boolean isUpvote) {
        var votes = new ArrayList<Vote>();
        for (var i=0; i < numberOfVotes; i++) {
            votes.add(new Vote(isUpvote ? "upVote" : "downVote", 12L));
        }
        return votes;
    }

}
