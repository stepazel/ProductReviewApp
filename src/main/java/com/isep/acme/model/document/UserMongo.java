package com.isep.acme.model.document;

import com.isep.acme.model.User;
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
    private String userId;

    private String username;
    private String password;
    private String fullName;
    private String nif;
    private String morada;
    
    private Set<RoleMongo>    roles = new HashSet<>();
    @DBRef
    private List<ReviewMongo> reviews;

    public UserMongo() {
    }

    public UserMongo(String username, String password, String fullName, String nif, String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.nif      = nif;
        this.morada   = morada;
        this.roles    = new HashSet<>();
    }

    public UserMongo(String username, String password, String fullName, String nif, String morada,
                     Set<RoleMongo> roles) {
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
        return user;
    }
}
