package com.isep.acme.property;

import com.isep.acme.repositories.ProductRepository;
import com.isep.acme.repositories.RatingRepository;
import com.isep.acme.repositories.ReviewRepository;
import com.isep.acme.repositories.UserRepository;
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
}
