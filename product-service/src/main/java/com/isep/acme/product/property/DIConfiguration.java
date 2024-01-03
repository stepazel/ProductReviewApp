package com.isep.acme.product.property;

import com.isep.acme.product.services.SKUGenerator;
import com.isep.acme.product.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DIConfiguration {
    @Autowired
    private ApplicationContext context;

    @Bean
    public ProductRepository ProductRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (ProductRepository) context.getBean("productRepository" + dbType);
    }

    @Bean
    public SKUGenerator SKUGeneratorAlias(@Value("${app.sku-generator}") String qualifier) {
        return (SKUGenerator) context.getBean("SKUGenerator" + qualifier);
    }
}
