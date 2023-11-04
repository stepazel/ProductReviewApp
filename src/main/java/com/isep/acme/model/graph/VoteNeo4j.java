package com.isep.acme.model.graph;

import com.isep.acme.model.Vote;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node("Vote")
public class VoteNeo4j {

    @Id
    @GeneratedValue
    public Long id;

    @Getter
    @Property
    private String vote;

    @Property
    private Long userID;


    public VoteNeo4j() {

    }

    public VoteNeo4j(String vote, Long userID) {
        this.vote   = vote;
        this.userID = userID;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vote toDomainModel() {
        return new Vote(vote, userID);
    }


}
