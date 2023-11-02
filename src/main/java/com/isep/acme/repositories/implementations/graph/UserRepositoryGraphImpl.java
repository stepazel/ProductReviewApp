package com.isep.acme.repositories.implementations.graph;

import com.isep.acme.model.User;
import com.isep.acme.model.graph.UserNeo4j;
import com.isep.acme.repositories.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Collectors;

@Component("userRepositoryGraph")
public class UserRepositoryGraphImpl implements UserRepository {
    private final UserRepositoryNeo4j userRepositoryNeo4j;

    public UserRepositoryGraphImpl(UserRepositoryNeo4j userRepositoryNeo4j) {
        this.userRepositoryNeo4j = userRepositoryNeo4j;
    }

    @Override
    public User save(User entity) {
        var user = entity.toGraphModel();
        var userNeo5j = userRepositoryNeo4j.save(user);
                var hm = userNeo5j.toDomainEntity();
                return hm;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepositoryNeo4j.findByUserId(userId).map(UserNeo4j::toDomainEntity);
    }

    @Override
    public User getById(Long userId) {
        return userRepositoryNeo4j.findByUserId(userId).get().toDomainEntity();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserNeo4j> user = userRepositoryNeo4j.findByUsername(username);
        return user.map(UserNeo4j::toDomainEntity);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepositoryNeo4j.findAll().stream().map(UserNeo4j::toDomainEntity).collect(Collectors.toList());
    }
}
