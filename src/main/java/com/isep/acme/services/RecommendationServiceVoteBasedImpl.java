package com.isep.acme.services;

import com.isep.acme.model.Review;
import com.isep.acme.model.ReviewDTO;
import com.isep.acme.model.ReviewMapper;
import com.isep.acme.model.Vote;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service("recommendationServiceVoteBased")
public class RecommendationServiceVoteBasedImpl implements RecommendationService {
    @Autowired
    private UserRepository   userRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * If the user votes at least fifty percent as me (this means matching upvotes and downvotes), all reviews of the
     * user (that I have not yet voted for) will be recommended to me
     *
     * @param userId the id of the user
     * @return a list of recommended reviews
     */
    @Override
    public List<ReviewDTO> getReviewRecommendedForUser(Long userId) {
        Map<Long, String>  currentUserVotes     = getCurrentUserVotes(userId);
        Map<Long, Integer> userSimilarityScores = calculateUserSimilarityScores(currentUserVotes);
        Set<Long>          similarUsers         = findSimilarUsers(userSimilarityScores, currentUserVotes.size());
        return getRecommendedReviews(similarUsers, currentUserVotes);
    }

    /**
     * Get all the votes of the current user
     *
     * @param userId the id of the user
     * @return a map of the votes of the current user
     */
    private Map<Long, String> getCurrentUserVotes(Long userId) {
        Map<Long, String> currentUserVotes = new HashMap<>();
        for (Review review : reviewRepository.findAll()) {
            review.getUpVote().stream().filter(vote -> vote.getUserID().equals(userId)).forEach(vote -> currentUserVotes.put(review.getIdReview(), "upVote"));

            review.getDownVote().stream().filter(vote -> vote.getUserID().equals(userId)).forEach(vote -> currentUserVotes.put(review.getIdReview(), "downVote"));
        }
        return currentUserVotes;
    }

    /**
     * Calculate the similarity scores of the current user with all other users
     *
     * @param currentUserVotes the votes of the current user
     * @return a map of the similarity scores of the current user with all other users
     */
    private Map<Long, Integer> calculateUserSimilarityScores(Map<Long, String> currentUserVotes) {
        Map<Long, Integer> userSimilarityScores = new HashMap<>();
        for (Review review : reviewRepository.findAll()) {
            if (!currentUserVotes.containsKey(review.getIdReview())) {
                continue;
            }
            String     currentUserVoteType = currentUserVotes.get(review.getIdReview());
            List<Long> upVoters            =
                    review.getUpVote().stream().map(Vote::getUserID).collect(Collectors.toList());
            List<Long> downVoters          =
                    review.getDownVote().stream().map(Vote::getUserID).collect(Collectors.toList());

            List<Long> similarVoters   = "upVote".equals(currentUserVoteType) ? upVoters : downVoters;
            List<Long> differentVoters = "upVote".equals(currentUserVoteType) ? downVoters : upVoters;

            similarVoters.forEach(id -> userSimilarityScores.merge(id, 1, Integer::sum));
            differentVoters.forEach(id -> userSimilarityScores.merge(id, -1, Integer::sum));
        }
        return userSimilarityScores;
    }

    // !!!!!!!!! I DONT KNOW IF THIS IS CORRECT, I MAY HAVE UNDERSTOOD THE REQUIREMENTS WRONG !!!!!!!!!

    /**
     * Find the users that have a similarity score of at least fifty percent with the current user
     *
     * @param userSimilarityScores  the similarity scores of the current user with all other users
     * @param totalCurrentUserVotes the total number of votes of the current user
     * @return a set of users that have a similarity score of at least fifty percent with the current user
     */
    private Set<Long> findSimilarUsers(Map<Long, Integer> userSimilarityScores, int totalCurrentUserVotes) {
        int halfOfCurrentUserVotes = totalCurrentUserVotes / 2;
        return userSimilarityScores.entrySet().stream().filter(entry -> entry.getValue() >= halfOfCurrentUserVotes).map(Map.Entry::getKey).collect(Collectors.toSet());
    }
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    /**
     * Get all the reviews of the users that have a similarity score of at least fifty percent with the current user
     *
     * @param similarUsers     the users that have a similarity score of at least fifty percent with the current user
     * @param currentUserVotes the votes of the current user
     * @return a list of recommended reviews
     */
    private List<ReviewDTO> getRecommendedReviews(Set<Long> similarUsers, Map<Long, String> currentUserVotes) {
        List<ReviewDTO> recommendedReviews = new ArrayList<>();
        for (Review review : reviewRepository.findAll()) {
            boolean hasCurrentUserVoted = currentUserVotes.containsKey(review.getIdReview());
            if (!hasCurrentUserVoted && similarUsers.contains(review.getUser().getUserId())) {
                recommendedReviews.add(ReviewMapper.toDto(review));
            }
        }
        return recommendedReviews;
    }
}
