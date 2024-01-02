package com.isep.api.gateway.services;

import com.isep.api.gateway.model.User;
import com.isep.api.gateway.model.dto.UserView;
import com.isep.api.gateway.mappers.UserViewMapper;
import com.isep.api.gateway.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final UserViewMapper userViewMapper;

    public UserService(@Qualifier("UserRepositoryAlias") UserRepository userRepo, UserViewMapper userViewMapper) {
        this.userRepo       = userRepo;
        this.userViewMapper = userViewMapper;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User "
                + "with username - %s, not found", username)));
    }

    public UserView getUser(final Long userId) {
        return userViewMapper.toUserView(userRepo.getById(userId));
    }

    public Optional<User> getUserId(Long user) {
        return userRepo.findById(user);
    }
}
