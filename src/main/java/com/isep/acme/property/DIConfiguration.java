package com.isep.acme.property;

import com.isep.acme.services.SKUGenerator;
import com.isep.acme.repositories.*;
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
    public RatingRepository RatingRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (RatingRepository) context.getBean("ratingRepository" + dbType);
    }

    @Bean
    public ReviewRepository ReviewRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (ReviewRepository) context.getBean("reviewRepository" + dbType);
    }

    @Bean
    public UserRepository UserRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (UserRepository) context.getBean("userRepository" + dbType);
    }

    @Bean
    public AggregatedRatingRepository AggregatedRatingRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (AggregatedRatingRepository) context.getBean("aggregatedRatingRepository" + dbType);
    }

    @Bean
    public ImageRepository ImageRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (ImageRepository) context.getBean("imageRepository" + dbType);
    }

    @Bean
    public SKUGenerator SKUGeneratorAlias(@Value("${app.sku-generator}") String qualifier) {
        return (SKUGenerator) context.getBean("SKUGenerator" + qualifier);
    }
}
