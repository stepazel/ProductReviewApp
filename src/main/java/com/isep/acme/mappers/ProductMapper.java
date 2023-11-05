package com.isep.acme.mappers;

import com.isep.acme.model.Product;
import com.isep.acme.model.document.ProductMongo;
import com.isep.acme.model.dto.ProductDTO;
import com.isep.acme.model.dto.ProductDetailDTO;
import com.isep.acme.model.graph.ProductNeo4j;

public class ProductMapper {
    public static ProductMongo toDocumentModel(Product product) {
        return new ProductMongo(product.getSku(), product.getDesignation(), product.getDesignation());
    }
    public static ProductNeo4j toGraphModel(Product product) {
        return new ProductNeo4j(product.getSku(), product.getDesignation(), product.getDescription());
    }

    public static ProductDetailDTO toDetailDto(Product product) {
        return new ProductDetailDTO(product.getSku(), product.getDesignation(), product.getDescription());
    }

    public static ProductDTO toDto(Product product) {
        return new ProductDTO(product.getSku(), product.getDesignation());
    }


}
