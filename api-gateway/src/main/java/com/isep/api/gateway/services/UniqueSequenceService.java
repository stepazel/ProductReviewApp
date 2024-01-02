package com.isep.api.gateway.services;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class UniqueSequenceService {

    private final MongoOperations mongoOperations;

    public UniqueSequenceService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long getNextSequence(String seqName) {
        // Get the sequence number and increment it
        Query  query  = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        CustomSequences counter = mongoOperations.findAndModify(query, update, options().returnNew(true).upsert(true)
                , CustomSequences.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}

@Document(collection = "customSequences")
class CustomSequences {
    @Id
    private String id;
    @Getter
    private Long   seq;
}
