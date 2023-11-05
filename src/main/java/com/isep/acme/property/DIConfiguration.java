package com.isep.acme.property;

import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.services.RecommendationService;
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
    public ProductRepository ProductRepositoryAlias(@Value("${implementations.repository}") String qualifier) {
        return (ProductRepository) context.getBean(qualifier);
    }

    @Bean
    public RecommendationService RecommendationServiceAlias(@Value("${app.recommendation-algorithm}") String qualifier) {
        return (RecommendationService) context.getBean("RecommendationService" + qualifier);
    }
}
