package com.isep.acme.model;

import com.isep.acme.model.document.VoteMongo;
import com.isep.acme.model.graph.VoteNeo4j;
import com.isep.acme.model.jpa.VoteJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class Vote {
    private String vote;
    private Long   userID;

    public Vote(String vote, Long userID) {
        this.vote   = vote;
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Vote vote1 = (Vote) o;
        return vote.equals(vote1.vote) && userID.equals(vote1.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote, userID);
    }

    public VoteNeo4j toGraphModel() {
        return new VoteNeo4j(vote, userID);
    }

    public VoteMongo toDocumentModel() {
        return new VoteMongo(vote, userID);
    }

    public VoteJpa toJpaModel() {
        return new VoteJpa(vote, userID);
    }
}
