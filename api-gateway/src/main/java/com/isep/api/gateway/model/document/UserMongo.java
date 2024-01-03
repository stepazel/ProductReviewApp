package com.isep.api.gateway.model.document;

import com.isep.api.gateway.model.User;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Document(collection = "users")
public class UserMongo {

    @Id
    private Long userId;

    private String username;
    private String password;
    private String fullName;
    private String nif;
    private String morada;

    private Set<RoleMongo>    roles = new HashSet<>();

    public UserMongo() {
    }

    public UserMongo(Long userId, String username, String password, String fullName, String nif, String morada) {
        this.userId   = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif      = nif;
        this.morada   = morada;
        this.roles    = new HashSet<>();
    }

    public UserMongo(Long userId, String username, String password, String fullName, String nif, String morada,
                     Set<RoleMongo> roles) {
        this.userId   = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif      = nif;
        this.morada   = morada;
        this.roles    = roles;
    }

    public User toDomainEntity() {
        User user = new User(username, password, fullName, nif, morada);
        user.setAuthorities(roles.stream().map(RoleMongo::toDomainEntity).filter(Objects::nonNull).collect(Collectors.toSet()));
        user.setUserId(userId);
        return user;
    }
}
