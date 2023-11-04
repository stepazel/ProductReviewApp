package com.isep.acme.model.document;

import com.isep.acme.model.Vote;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "votes")
public class VoteMongo {

    @Id
    private String id;

    @Getter
    private String vote;
    private Long   userID;

    public VoteMongo() {
    }

    public VoteMongo(String vote, Long userID) {
        this.vote   = vote;
        this.userID = userID;
    }

    public Vote toDomainModel() {
        return new Vote(vote, userID);
    }
}
