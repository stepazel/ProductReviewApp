package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.document.UserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepositoryMongo extends MongoRepository<UserMongo, String> {

    Optional<UserMongo> findByUsername(String username);
}
