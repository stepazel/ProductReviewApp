package com.isep.acme.repositories.implementations.document;

import com.isep.acme.model.User;
import com.isep.acme.model.document.UserMongo;
import com.isep.acme.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component("userRepositoryDocument")
public class UserRepositoryDocumentImpl implements UserRepository {
    private final UserRepositoryMongo userRepositoryMongo;

    public UserRepositoryDocumentImpl(UserRepositoryMongo userRepositoryMongo) {
        this.userRepositoryMongo = userRepositoryMongo;
    }

    @Override
    public User save(User user) {
        return userRepositoryMongo.save(user.toDocumentModel()).toDomainEntity();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepositoryMongo.findById(String.valueOf(userId)).map(UserMongo::toDomainEntity);
    }

    @Override
    public User getById(Long userId) {
        return userRepositoryMongo.findById(String.valueOf(userId)).orElseThrow(() -> new RuntimeException("User not "
                + "found")).toDomainEntity();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        var usr = userRepositoryMongo.findByUsername(username);
        if (usr.isEmpty()) {
            return Optional.empty();
        }
        return usr.map(UserMongo::toDomainEntity);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepositoryMongo.findAll().stream().map(UserMongo::toDomainEntity).collect(Collectors.toList());
    }
}
