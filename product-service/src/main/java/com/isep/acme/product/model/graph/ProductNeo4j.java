package com.isep.acme.product.model.graph;


import com.isep.acme.product.model.Product;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

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

//    @Relationship(type = "REVIEWED", direction = Relationship.Direction.INCOMING)
//    private final List<ReviewNeo4j> reviewNeo4js = new ArrayList<>();

    public ProductNeo4j(String sku, String designation, String description) {
        this.sku         = sku;
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

    public Product toDomainEntity() {
        return new Product(this.sku, this.GetDesignation(), this.GetDescription());
    }
}
