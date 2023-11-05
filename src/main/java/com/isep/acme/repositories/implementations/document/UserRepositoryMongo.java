package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.document.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserMongo, Long> {

    @Query("{ '_id' : ?0 }")
    Optional<UserMongo> findById(Long id);
    
    Optional<UserMongo> findByUsername(String username);
}
