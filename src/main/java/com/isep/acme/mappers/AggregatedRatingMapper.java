package com.isep.acme.mappers;

import com.isep.acme.model.AggregatedRating;
import com.isep.acme.model.document.AggregatedRatingMongo;
import com.isep.acme.model.graph.AggregatedRatingNeo4j;

public class AggregatedRatingMapper {

    public static AggregatedRatingNeo4j toGraphModel(AggregatedRating aggregatedRating) {
        var aggregatedRatingGraph = new AggregatedRatingNeo4j(aggregatedRating.getAverage(), aggregatedRating.getProduct());
        aggregatedRatingGraph.setId(aggregatedRatingGraph.getId());
        return aggregatedRatingGraph;
    }

    public static AggregatedRatingMongo toDocumentModel(AggregatedRating aggregatedRating) {
        return new AggregatedRatingMongo(aggregatedRating.getAverage(), aggregatedRating.getProduct());
    }
}
