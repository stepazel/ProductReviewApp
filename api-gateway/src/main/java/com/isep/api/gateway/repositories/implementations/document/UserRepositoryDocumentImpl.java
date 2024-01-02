package com.isep.api.gateway.repositories.implementations.document;

import com.isep.api.gateway.model.User;
import com.isep.api.gateway.model.document.UserMongo;
import com.isep.api.gateway.repositories.UserRepository;
import com.isep.api.gateway.services.UniqueSequenceService;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component("userRepositoryDocument")
public class UserRepositoryDocumentImpl implements UserRepository {
    private final UserRepositoryMongo   userRepositoryMongo;
    private final UniqueSequenceService uniqueSequenceService;

    public UserRepositoryDocumentImpl(UserRepositoryMongo userRepositoryMongo,
                                      UniqueSequenceService uniqueSequenceService) {
        this.userRepositoryMongo   = userRepositoryMongo;
        this.uniqueSequenceService = uniqueSequenceService;
    }

    @Override
    public User save(User user) {
        user.setUserId(uniqueSequenceService.getNextSequence("user"));
        return userRepositoryMongo.save(user.toDocumentModel()).toDomainEntity();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepositoryMongo.findById(userId).map(UserMongo::toDomainEntity);
    }

    @Override
    public User getById(Long userId) {
        return userRepositoryMongo.findById(userId).orElseThrow(() -> new RuntimeException("User not " + "found")).toDomainEntity();
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
