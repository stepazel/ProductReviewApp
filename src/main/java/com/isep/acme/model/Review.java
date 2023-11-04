package com.isep.acme.model;

import com.isep.acme.model.document.ReviewMongo;
import com.isep.acme.model.graph.ReviewNeo4j;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Review {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idReview;

    @Getter
    @Version
    private long version;

    @Getter
    @Column(nullable = false)
    private String approvalStatus;

    @Getter
    @Column(nullable = false)
    private String reviewText;

    @Getter
    @ElementCollection
    @Column(nullable = true)
    private List<Vote> upVote;

    @Getter
    @ElementCollection
    @Column(nullable = true)
    private List<Vote> downVote;

    @Column(nullable = true)
    private String report;

    @Getter
    @Column(nullable = false)
    private LocalDate publishingDate;

    @Getter
    @Column(nullable = false)
    private String funFact;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private Rating rating;

    protected Review() {
    }

    public Review(final Long idReview, final long version, final String approvalStatus, final String reviewText,
                  final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version  = Objects.requireNonNull(version);
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

    public void setReport(String report) {
        this.report = report;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rating getRating() {
        if (rating == null) {
            return new Rating(0.0);
        }
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public void setUpVote(List<Vote> upVote) {
        this.upVote = upVote;
    }

    public void setDownVote(List<Vote> downVote) {
        this.downVote = downVote;
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
        return new ReviewMongo(approvalStatus, reviewText, upVote, downVote, report, publishingDate, funFact, product
                , rating, user);
    }
}
