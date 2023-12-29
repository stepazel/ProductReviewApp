package com.isep.acme.model.graph;

import com.isep.acme.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Node("Review")
public class ReviewNeo4j {

    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Property
    private String approvalStatus;

    @Getter
    @Property
    private String reviewText;

    @Relationship(type = "VOTED", direction = Relationship.Direction.INCOMING)
    @Property
    private List<VoteNeo4j> votes = new ArrayList<>();

    @Property
    private String report;

    @Getter
    @Property
    private LocalDate publishingDate;

    @Getter
    @Property
    private String funFact;

    @Getter
    @Relationship(type = "REVIEWED", direction = Relationship.Direction.OUTGOING)
    private ProductNeo4j product;

    @Getter
    @Relationship(type = "VOTED", direction = Relationship.Direction.INCOMING)
    private UserNeo4j user;

    @Relationship(type = "RATED", direction = Relationship.Direction.OUTGOING)
    private RatingNeo4j rating;

    protected ReviewNeo4j() {
    }

    public ReviewNeo4j(final long version, final String approvalStatus, final String reviewText,
                       final LocalDate publishingDate, final String funFact) {
        this.id = null;
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public ReviewNeo4j(final long version, final String approvalStatus, final String reviewText,
                       final List<Vote> upVote, final List<Vote> downVote, final String report,
                       final LocalDate publishingDate, final String funFact, Product product, Rating rating,
                       User user) {
        this(version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public ReviewNeo4j(String approvalStatus, String reviewText, List<Vote> votes, String report,
                       LocalDate publishingDate, String funFact, ProductNeo4j product, UserNeo4j user,
                       RatingNeo4j rating) {
        this.approvalStatus = approvalStatus;
        this.reviewText     = reviewText;
        this.votes          = votes.stream().map(Vote::toGraphModel).collect(Collectors.toList());
        this.report         = report;
        this.publishingDate = publishingDate;
        this.funFact        = funFact;
        this.product        = product;
        this.user           = user;
        this.rating         = rating;
    }

    public ReviewNeo4j(final String reviewText, LocalDate publishingDate, Product product, String funFact,
                       Rating rating, User user) {
        setReviewText(reviewText);
        setProduct(product);
        setPublishingDate(publishingDate);
        setApprovalStatus("pending");
        setFunFact(funFact);
        setRating(rating);
        setUser(user);
        this.votes = new ArrayList<>();
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

    //    public long getVersion() {
    //        return version;
    //    }

    public void setFunFact(String funFact) {
        this.funFact = funFact;
    }

    public void setProduct(Product product) {
        this.product = product.toGraphModel();
    }

    public void setUser(User user) {
        this.user = user.toGraphModel();
    }

    public RatingNeo4j getRating() {
        if (rating == null) {
            //            return new RatingNeo4j(0.0);
            return new RatingNeo4j(0.0);
        }
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating.toGraphModel();
    }


    public void setUpVote(List<Vote> upVotes) {
        this.votes.addAll(upVotes.stream().map(Vote::toGraphModel).collect(Collectors.toList()));
    }


    public void setDownVote(List<Vote> downVotes) {
        this.votes.addAll(downVotes.stream().map(Vote::toGraphModel).collect(Collectors.toList()));
    }

    public Review toDomainModel() {
        return new Review(id, 0, approvalStatus, reviewText,
                votes.stream().filter(voteNeo4j -> Objects.equals(voteNeo4j.getVote(), "upVote")).map(VoteNeo4j::toDomainModel).collect(Collectors.toList()), votes.stream().filter(voteNeo4j -> Objects.equals(voteNeo4j.getVote(), "downVote")).map(VoteNeo4j::toDomainModel).collect(Collectors.toList()), report, publishingDate, funFact, product.toDomainEntity(), rating.toDomainEntity(), user.toDomainEntity());
    }
}
