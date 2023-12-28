package com.isep.acme.model;

import com.isep.acme.model.document.RoleMongo;
import com.isep.acme.model.graph.RoleNeo4j;
import com.isep.acme.model.jpa.RoleJpa;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;

@Value
@AllArgsConstructor
public class Role implements GrantedAuthority {

    public static final String ADMIN = "Admin";

    public static final String MOD = "Mod";

    public static final String REGISTERED_USER = "RegisteredUser";

    String authority;

    public RoleNeo4j toGraphModel() {
        return new RoleNeo4j(this.authority);
    }

    public RoleMongo toDocumentModel() {
        return new RoleMongo(this.authority);
    }

    public RoleJpa toJpaModel() {
        return new RoleJpa(this.authority);
    }
}
