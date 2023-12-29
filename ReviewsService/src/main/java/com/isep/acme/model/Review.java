package com.isep.acme.model;

import com.isep.acme.model.document.ReviewMongo;
import com.isep.acme.model.graph.ReviewNeo4j;
import com.isep.acme.model.jpa.ReviewJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class Review {

    private Long       idReview;
    private long       version;
    private String     approvalStatus;
    private String     reviewText;
    private List<Vote> upVote;
    private List<Vote> downVote;
    private String     report;
    private LocalDate  publishingDate;
    private String     funFact;
    private Product    product;
    private User       user;
    private Rating     rating;

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText,
            final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version  = version;
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText,
            final List<Vote> upVote, final List<Vote> downVote, final String report,
            final LocalDate publishingDate, final String funFact, Product product, Rating rating, User user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);
    }

    public Review(final String reviewText, LocalDate publishingDate, Product product, String funFact, Rating rating,
            User user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
        this.upVote   = new ArrayList<>();
        this.downVote = new ArrayList<>();
    }

    public Review(final String approvalStatus, final String reviewText, final List<Vote> upVote,
            final List<Vote> downVote, final String report, final LocalDate publishingDate,
            final String funFact, Product product, Rating rating, User user) {
        this(approvalStatus, reviewText, publishingDate, funFact);
        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);
    }

    public Review(String approvalStatus, String reviewText, LocalDate publishingDate, String funFact) {
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public Boolean setApprovalStatus(String approvalStatus) {

        if (approvalStatus.equalsIgnoreCase("pending") || approvalStatus.equalsIgnoreCase("approved") || approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public void setReviewText(String reviewText) {
        if (reviewText == null || reviewText.isBlank()) {
            throw new IllegalArgumentException("Review Text is a mandatory attribute of Review.");
        }
        if (reviewText.length() > 2048) {
            throw new IllegalArgumentException("Review Text must not be greater than 2048 characters.");
        }

        this.reviewText = reviewText;
    }

    public Rating getRating() {
        if (rating == null) {
            return new Rating(0.0);
        }
        return rating;
    }

    public boolean addUpVote(Vote upVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.upVote.contains(upVote)) {
            this.upVote.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(Vote downVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.downVote.contains(downVote)) {
            this.downVote.add(downVote);
            return true;
        }
        return false;
    }

    public ReviewNeo4j toGraphModel() {
        var review = new ReviewNeo4j(0, approvalStatus, reviewText, upVote, downVote, report, publishingDate, funFact
                , product, rating, user);
        review.setId(idReview);
        return review;
    }

    public ReviewMongo toDocumentModel() {
        return new ReviewMongo(idReview, approvalStatus, reviewText, upVote, downVote, report, publishingDate,
                funFact, product, rating, user);
    }

    public ReviewJpa toJpaModel() {
        return new ReviewJpa(approvalStatus, reviewText, upVote, downVote, report, publishingDate,
                funFact, product, rating, user);
    }

    public int getCountOfVotes() {
        return downVote.size() + upVote.size();
    }
}
