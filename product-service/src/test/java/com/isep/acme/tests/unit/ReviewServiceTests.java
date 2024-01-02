package com.isep.acme.tests.unit;

import com.isep.acme.model.*;
import com.isep.acme.model.dto.VoteReviewDTO;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.services.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class ReviewServiceTests {
    @MockBean
    private ReviewRepository reviewRepository;

    @Test
    void addingVote_withCorrectVoteName_shouldReturnTrue() {
        var review = new Review(
                "test",
                LocalDate.now(),
                Mockito.mock(Product.class),
                "funfact",
                Mockito.mock(Rating.class),
                Mockito.mock(User.class));
        review.setApprovalStatus("approved");
        Mockito.when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        Mockito.when(reviewRepository.save(review)).thenReturn(review);
        var reviewService = new ReviewServiceImpl(reviewRepository);
        assert reviewService.addVoteToReview(1L, new VoteReviewDTO(1L, "upVote"));
    }

    @Test
    void addingVote_withWrongVoteName_shouldReturnFalse() {
        var review = new Review(
                "test",
                LocalDate.now(),
                Mockito.mock(Product.class),
                "funfact",
                Mockito.mock(Rating.class),
                Mockito.mock(User.class));
        review.setApprovalStatus("approved");
        Mockito.when(reviewRepository.findById(1L)).thenReturn(Optional.of(review));
        Mockito.when(reviewRepository.save(review)).thenReturn(review);
        var reviewService = new ReviewServiceImpl(reviewRepository);
        assert !reviewService.addVoteToReview(1L, new VoteReviewDTO(1L, "incorrect"));
    }
}
