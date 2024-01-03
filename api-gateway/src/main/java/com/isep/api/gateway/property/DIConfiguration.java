package com.isep.api.gateway.property;

import com.isep.api.gateway.repositories.UserRepository;
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
    public UserRepository UserRepositoryAlias(@Value("${app.database.type}") String dbType) {
        return (UserRepository) context.getBean("userRepository" + dbType);
    }
}
