package com.isep.acme.model.document;

import com.isep.acme.model.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Document(collection = "reviews")
public class ReviewMongo {

    @Id
    private String id;

    private String approvalStatus;
    private String reviewText;

    @Setter
    private List<VoteMongo> votes = new ArrayList<>();
    @Setter
    private String          report;
    @Setter
    private LocalDate       publishingDate;
    @Setter
    private String          funFact;

    @Setter
    @DBRef
    private ProductMongo product;

    @Setter
    @DBRef
    private UserMongo user;

    @Setter
    @DBRef
    private RatingMongo rating;

    public ReviewMongo() {
    }

    public ReviewMongo(final String approvalStatus, final String reviewText, final List<Vote> upVote,
                       final List<Vote> downVote, final String report, final LocalDate publishingDate,
                       final String funFact, Product product, Rating rating, User user) {
        this(approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product.toDocumentModel());
        setRating(rating.toDocumentModel());
        setUser(user.toDocumentModel());
    }

    public ReviewMongo(String approvalStatus, String reviewText, LocalDate publishingDate, String funFact) {
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public Review toDomainModel() {
        return new Review(approvalStatus, reviewText,
                votes.stream().filter(voteMongo -> "upVote".equals(voteMongo.getVote())).map(VoteMongo::toDomainModel).collect(Collectors.toList()), votes.stream().filter(voteMongo -> "downVote".equals(voteMongo.getVote())).map(VoteMongo::toDomainModel).collect(Collectors.toList()), report, publishingDate, funFact, product.toDomainEntity(), rating.toDomainEntity(), user.toDomainEntity());
    }

    public Boolean setApprovalStatus(String approvalStatus) {

        if (approvalStatus.equalsIgnoreCase("pending") || approvalStatus.equalsIgnoreCase("approved") || approvalStatus.equalsIgnoreCase("rejected")) {

            this.approvalStatus = approvalStatus;
            return true;
        }
        return false;
    }

    public RatingMongo getRating() {
        return Objects.requireNonNullElseGet(rating, () -> new RatingMongo(0.0));
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


    public void setUpVote(List<Vote> upVotes) {
        this.votes.addAll(upVotes.stream().map(Vote::toDocumentModel).collect(Collectors.toList()));
    }


    public void setDownVote(List<Vote> downVotes) {
        this.votes.addAll(downVotes.stream().map(Vote::toDocumentModel).collect(Collectors.toList()));
    }
}
