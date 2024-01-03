package com.isep.api.gateway.repositories;

import com.isep.api.gateway.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User entity);

    Optional<User> findById(Long userId);

    User getById(final Long userId);

    Optional<User> findByUsername(String username);

    Iterable<User> findAll();
}
