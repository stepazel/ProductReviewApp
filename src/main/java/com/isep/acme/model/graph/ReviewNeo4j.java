package com.isep.acme.model.graph;

import com.isep.acme.model.Product;
import com.isep.acme.model.Rating;
import com.isep.acme.model.User;
import com.isep.acme.model.Vote;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Node("Review")
public class ReviewNeo4j {

    @Getter
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
    private List<Vote> votes;

    private List<Vote> downVotes = new ArrayList<Vote>();

    private List<Vote> upVotes = new ArrayList<Vote>();

    @Property
    private String report;

    @Property
    private LocalDate publishingDate;

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

    public ReviewNeo4j(final long version, final String approvalStatus, final String reviewText, final LocalDate publishingDate, final String funFact) {
        this.id = null;
        setApprovalStatus(approvalStatus);
        setReviewText(reviewText);
        setPublishingDate(publishingDate);
        setFunFact(funFact);
    }

    public ReviewNeo4j(final long version, final String approvalStatus, final String reviewText, final List<Vote> upVote, final List<Vote> downVote, final String report, final LocalDate publishingDate, final String funFact, Product product, Rating rating, User user) {
        this(version, approvalStatus, reviewText, publishingDate, funFact);

        setUpVote(upVote);
        setDownVote(downVote);
        setReport(report);
        setProduct(product);
        setRating(rating);
        setUser(user);

    }

    public ReviewNeo4j(final String reviewText, LocalDate publishingDate, Product product, String funFact, Rating rating, User user) {
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

        if (approvalStatus.equalsIgnoreCase("pending") ||
                approvalStatus.equalsIgnoreCase("approved") ||
                approvalStatus.equalsIgnoreCase("rejected")) {

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
        if (report.length() > 2048) {
            throw new IllegalArgumentException("Report must not be greater than 2048 characters.");
        }
        this.report = report;
    }

    public LocalDate getPublishingDate() {
        return publishingDate;
    }

    public void setPublishingDate(LocalDate publishingDate) {
        this.publishingDate = publishingDate;
    }

//    public long getVersion() {
//        return version;
//    }

    public String getFunFact() {
        return funFact;
    }

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
            return new RatingNeo4j();
        }
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating.toGraphModel();
    }

    public List<Vote> getUpVote() {
        return upVotes;
    }

    public void setUpVote(List<Vote> upVotes) {
        this.upVotes = upVotes;
    }

    public List<Vote> getDownVote() {
        return downVotes;
    }

    public void setDownVote(List<Vote> downVotes) {
        this.downVotes = downVotes;
    }

    public boolean addUpVote(Vote upVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.upVotes.contains(upVote)) {
            this.upVotes.add(upVote);
            return true;
        }
        return false;
    }

    public boolean addDownVote(Vote downVote) {

        if (!this.approvalStatus.equals("approved"))
            return false;

        if (!this.downVotes.contains(downVote)) {
            this.downVotes.add(downVote);
            return true;
        }
        return false;
    }
}
