package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.model.User;
import com.isep.acme.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("userRepositoryRelational")
public class UserRepositoryRelational implements UserRepository {
    private UserRepositoryJPA userRepositoryJPA;

    public UserRepositoryRelational(UserRepositoryJPA userRepositoryJPA) {
        this.userRepositoryJPA = userRepositoryJPA;
    }

    @Override
    public User save(User entity) {
        return userRepositoryJPA.save(entity);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepositoryJPA.findById(userId);
    }

    @Override
    public User getById(Long userId) {
        return userRepositoryJPA.getById(userId);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepositoryJPA.findByUsername(username);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepositoryJPA.findAll();
    }
}
