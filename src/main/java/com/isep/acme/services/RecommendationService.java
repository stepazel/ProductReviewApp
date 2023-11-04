package com.isep.acme.services;

import com.isep.acme.model.dto.ReviewDTO;

import java.util.List;

public interface RecommendationService {
    List<ReviewDTO> getReviewRecommendedForUser(Long userId);
}
