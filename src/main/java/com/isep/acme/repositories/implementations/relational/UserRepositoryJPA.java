package com.isep.acme.repositories.implementations.relational;

import com.isep.acme.controllers.ResourceNotFoundException;
import com.isep.acme.model.User;
import com.isep.acme.model.jpa.UserJpa;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@CacheConfig(cacheNames = "users")
public interface UserRepositoryJPA extends CrudRepository<UserJpa, Long> {

    @Override
    @Caching(evict = {@CacheEvict(key = "#p0.userId", condition = "#p0.userId != null"), @CacheEvict(key = "#p0" +
                                                                                                           ".username", condition = "#p0.username != null")})
    <S extends UserJpa> S save(S entity);

    @Override
    @Cacheable
    Optional<UserJpa> findById(Long userId);

    @Cacheable
    default User getById(final Long userId) {
        final Optional<UserJpa> optionalUser = findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        if (!optionalUser.get().isEnabled()) {
            throw new ResourceNotFoundException(User.class, userId);
        }
        return optionalUser.get().toDomainEntity();
    }

    @Cacheable
    Optional<User> findByUsername(String username);


}
