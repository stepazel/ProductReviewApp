package com.isep.acme.model.document;

import com.isep.acme.model.Vote;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "votes")
public class VoteMongo {

    @Id
    private Long id;

    @Getter
    private String vote;
    private Long   userID;

    public VoteMongo() {
    }

    public VoteMongo(String vote, Long userID) {
        this.vote   = vote;
        this.userID = userID;
    }

    public VoteMongo(Long id, String vote, Long userID) {
        this.id     = id;
        this.vote   = vote;
        this.userID = userID;
    }

    public Vote toDomainModel() {
        return new Vote(vote, userID);
    }
}
