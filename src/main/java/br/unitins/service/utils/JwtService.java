package br.unitins.service.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.inject.Singleton;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.model.Family;
import br.unitins.model.Therapist;
import br.unitins.model.User;
import br.unitins.model.enums.Role;
import io.smallrye.jwt.build.Jwt;

@Singleton
public class JwtService {
    public String generateJwt(User user) {
        Set<String> roles = user.roles
                .stream().map(r -> r.getLabel())
                .collect(Collectors.toSet());

        // 1 hora = 3600000
        long duration = System.currentTimeMillis() + 3600000;

        // upn - User Principal Name
        return Jwt.issuer("unitins-jwt")
                .claim("id", user.id)
                .claim("name", user.name)
                .claim("email", user.email)
                .upn(user.email)
                .groups(roles)
                .expiresAt(duration)
                .sign();
    }

    public Long getUserId(JsonWebToken token) {
        return Long.parseLong(token.<Object>getClaim("id").toString());
    }

    public User getUser(JsonWebToken token) {
        HashSet<Role> roles = new HashSet<>();

        User user = new User();

        String[] groupRoles = token.getGroups().toArray(String[]::new);

        for (String role : groupRoles) {
            if (role.equals(Role.THERAPIST.getLabel())) {
                user = new Therapist();
            }
            if (role.equals(Role.FAMILY.getLabel())) {
                user = new Family();
            }
            roles.add(Role.valueOfRole(role));
        }

        user.id = Long.parseLong(token.<Object>getClaim("id").toString());
        user.name = token.<Object>getClaim("name").toString();
        user.email = token.<Object>getClaim("email").toString();
        user.roles = roles;

        return user;
    }
}
