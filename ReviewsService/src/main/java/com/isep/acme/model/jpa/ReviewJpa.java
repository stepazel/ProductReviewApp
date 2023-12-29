package com.isep.acme.model.jpa;

import com.isep.acme.model.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
public class ReviewJpa {

    @Getter
    @Setter
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
    private List<VoteJpa> votes;

    @Setter
    @Column(nullable = true)
    private String report;

    @Setter
    @Getter
    @Column(nullable = false)
    private LocalDate publishingDate;

    @Setter
    @Getter
    @Column(nullable = false)
    private String funFact;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductJpa product;

    @Setter
    @Getter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpa user;

    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    private RatingJpa rating;

    protected ReviewJpa() {
    }

    public ReviewJpa(final Long idReview, final long version, final String approvalStatus, final String reviewText,
            final LocalDate publishingDate, final String funFact) {
        this.idReview = Objects.requireNonNull(idReview);
        this.version  = Objects.requireNonNull(version);
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public ReviewJpa(final Long idReview, final long version, final String approvalStatus, final String reviewText,
            final List<Vote> upVote, final List<Vote> downVote, final String report,
            final LocalDate publishingDate, final String funFact, ProductJpa product, RatingJpa rating, UserJpa user) {
        this(idReview, version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);
    }

    public ReviewJpa(final String reviewText, LocalDate publishingDate, ProductJpa product, String funFact, RatingJpa rating,
            UserJpa user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
        this.votes = new ArrayList<>();
    }

    public ReviewJpa(final String approvalStatus, final String reviewText, final List<Vote> upVote,
            final List<Vote> downVote, final String report, final LocalDate publishingDate,
            final String funFact, Product product, Rating rating, User user) {
        this(approvalStatus, reviewText, publishingDate, funFact);
        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product.toJpaModel());
        setRating(rating.toJpaModel());
        setUser(user.toJpaModel());
    }

    public ReviewJpa(String approvalStatus, String reviewText, LocalDate publishingDate, String funFact) {
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public void setUpVote(List<Vote> upVotes) {
        this.votes.addAll(upVotes.stream().map(Vote::toJpaModel).collect(Collectors.toList()));
    }

    public void setDownVote(List<Vote> downVotes) {
        this.votes.addAll(downVotes.stream().map(Vote::toJpaModel).collect(Collectors.toList()));
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

    public RatingJpa getRating() {
        if (rating == null) {
            return new RatingJpa(0.0);
        }
        return rating;
    }

    public boolean addUpVote(VoteJpa upVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.votes.contains(upVote)) {
            this.votes.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(VoteJpa downVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.votes.contains(downVote)) {
            this.votes.add(downVote);
            return true;
        }

        return false;
    }

    public int getCountOfVotes() {
        return this.votes.size();
    }

    public Review toDomainEntity() {
        return new Review(this.idReview, this.version, this.approvalStatus, this.reviewText,
                this.votes.stream()
                          .filter(voteJpa -> "upVote".equals(voteJpa.getVote()))
                          .map(VoteJpa::toDomainEntity)
                          .collect(Collectors.toList()), this.votes.stream()
                                                                   .filter(voteJpa -> "downVote".equals(voteJpa.getVote()))
                                                                   .map(VoteJpa::toDomainEntity)
                                                                   .collect(Collectors.toList()), this.report, this.publishingDate, this.funFact,
                this.product.toDomainEntity(), this.rating.toDomainEntity(), this.user.toDomainEntity());
    }
}
