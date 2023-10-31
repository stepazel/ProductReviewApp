package com.isep.acme.model.graph;


import com.isep.acme.model.Product;
import org.springframework.data.neo4j.core.schema.*;

import java.util.ArrayList;
import java.util.List;

@Node("Product")
public class ProductNeo4j {

    @Id
    public final String sku;

    @Property()
    private final String designation;

    @Property()
    private final String description;

    @Relationship(type = "REVIEWED", direction = Relationship.Direction.INCOMING)
    private List<ReviewNeo4j> reviewNeo4js = new ArrayList<>();

    public ProductNeo4j(String sku, String designation, String description) {
        this.sku = sku;
        this.designation = designation;
        this.description = description;
    }

    public String GetSku() {
        return this.sku;
    }

    public String GetDesignation() {
        return this.designation;
    }

    public String GetDescription() {
        return this.description;
    }

    public Product ToDomainEntity() {
        return new Product(this.sku, this.GetDesignation(), this.GetDescription());
    }
}
