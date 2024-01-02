package com.isep.api.gateway.model;

import com.isep.api.gateway.model.document.UserMongo;
import com.isep.api.gateway.model.graph.UserNeo4j;
import com.isep.api.gateway.model.jpa.UserJpa;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    private static final long      serialVersionUID = 1L;
    private              Long      userId;
    private              String    username;
    private              String    password;
    private              String    fullName;
    private              Set<Role> authorities      = new HashSet<>();
    private              String    nif;
    private              String    morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public User(final String username, final String password, final String fullName, final String nif,
            final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public void addAuthority(Role r) {
        authorities.add(r);
    }

    public void setNif(String nif) {
        if (nif.length() != 9) {
            throw new IllegalArgumentException("NIF must be 9 characters.");
        }
        this.nif = nif;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserNeo4j toGraphModel() {
        return new UserNeo4j(userId, username, password, fullName, nif, morada,
                authorities.stream().map(Role::toGraphModel).collect(Collectors.toSet()));
    }

    public UserMongo toDocumentModel() {
        return new UserMongo(userId, username, password, fullName, nif, morada,
                authorities.stream().map(Role::toDocumentModel).collect(Collectors.toSet()));
    }

    public UserJpa toJpaModel() {
        return new UserJpa(userId, username, password, fullName, nif, morada,
                authorities.stream().map(Role::toJpaModel).collect(Collectors.toSet()));
    }
}

