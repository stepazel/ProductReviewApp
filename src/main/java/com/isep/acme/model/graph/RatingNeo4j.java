package com.isep.acme.model.graph;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
public class RatingNeo4j {
    @Id
    private Long idRating;

    @Property
    private Double rate;
}
