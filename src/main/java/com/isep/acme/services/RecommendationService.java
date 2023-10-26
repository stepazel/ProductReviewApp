package com.isep.acme.services;

import com.isep.acme.model.ReviewDTO;

import java.util.List;

public interface RecommendationService {
    List<ReviewDTO> getReviewRecommendedForUser(Long userId);
}
