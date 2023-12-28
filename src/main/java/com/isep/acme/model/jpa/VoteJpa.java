package com.isep.acme.model.jpa;

import com.isep.acme.model.Vote;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
@NoArgsConstructor
public class VoteJpa {
    private String vote;
    private Long   userID;

    public VoteJpa(String vote, Long userID) {
        this.vote   = vote;
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        VoteJpa vote1 = (VoteJpa) o;
        return vote.equals(vote1.vote) && userID.equals(vote1.userID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vote, userID);
    }

    public Vote toDomainEntity() {
        return new Vote(vote, userID);
    }
}
