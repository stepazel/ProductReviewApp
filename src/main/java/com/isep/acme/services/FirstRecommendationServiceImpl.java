package com.isep.acme.services;

import com.isep.acme.model.Review;
import com.isep.acme.model.dto.ReviewDTO;
import com.isep.acme.mappers.ReviewMapper;
import com.isep.acme.repositories.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

public class FirstRecommendationServiceImpl implements RecommendationService {
    private final ReviewRepository reviewRepository;

    public FirstRecommendationServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> getReviewRecommendedForUser(Long userId) {
        var reviews = reviewRepository.findAll();
        var recommendedReviews = new ArrayList<Review>();
        for (Review review : reviews) {
            if (review.getCountOfVotes() >= 4 && (double) review.getUpVote().size() / review.getCountOfVotes() > 0.65) {
                recommendedReviews.add(review);
            }
        }
        return ReviewMapper.toDtoList(recommendedReviews);
    }
}
