package br.unitins.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import br.unitins.model.Users;
import io.smallrye.jwt.build.Jwt;

@Singleton
public class JwtService {
    public String generateJwt(Users user) {
        Set<String> roles = user.roles
                .stream().map(r -> r.getLabel())
                .collect(Collectors.toSet());

        // 1 hora = 3600000
        long duration = System.currentTimeMillis() + 3600000;

        // upn - User Principal Name
        return Jwt.issuer("unitins-jwt")
                .claim("email", user.email)
                .claim("name", user.name)
                .upn(user.email)
                .groups(roles)
                .expiresAt(duration)
                .sign();
    }
}
