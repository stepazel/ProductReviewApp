package com.isep.api.gateway.model.jpa;

import com.isep.api.gateway.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserJpa implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    @Getter
    @GeneratedValue
    private Long userId;

    @Column(unique = true)
    @Email
    private String username;

    private String password;

    private String fullName;

    @ElementCollection
    private Set<RoleJpa> authorities = new HashSet<>();

    @Column(nullable = false, unique = true)
    private String nif;

    @Column(nullable = false)
    private String morada;

/*    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> review = new ArrayList<Review>(); */

    public UserJpa(final String username, final String password) {
        this.username = username;
        this.password = password;
    }


    public UserJpa(final String username, final String password, final String fullName, final String nif,
            final String morada) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        setNif(nif);
        this.morada = morada;
    }

    public UserJpa(Long userId, String username, String password, String fullName, String nif, String morada,
            Set<RoleJpa> roles) {
        this.userId      = userId;
        this.username    = username;
        this.password    = password;
        this.fullName    = fullName;
        this.nif         = nif;
        this.morada      = morada;
        this.authorities = roles;
    }

    public void addAuthority(RoleJpa r) {
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

    public User toDomainEntity() {
        var user = new User(username, password, fullName, nif, morada);
        user.setAuthorities(authorities.stream().map(RoleJpa::toDomainEntity).collect(Collectors.toSet()));
        user.setUserId(userId);
        return user;
    }
}

