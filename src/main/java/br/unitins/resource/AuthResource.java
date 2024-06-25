package br.unitins.resource;

import java.net.URI;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.dto.AuthDTO;
import br.unitins.dto.RegisterDTO;
import br.unitins.model.Users;
import br.unitins.model.enums.Role;
import br.unitins.repository.UserRepository;
import br.unitins.service.JwtService;
import br.unitins.service.PasswordService;

@Path("/auth")
public class AuthResource {
    @Inject
    UserRepository repository;

    @Inject
    PasswordService pService;

    @Inject
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Valid AuthDTO user) {
        String hash = pService.getHash(user.getPassword());

        Users validUser = repository.findByEmailAndPassword(user.getEmail(), hash);

        if (validUser == null){
            return Response
                .status(Status.NO_CONTENT)
                .entity("Usuário não encontrado.")
                .build();
        } else {
            return Response.noContent().header(
                "Authorization",
                jwtService.generateJwt(validUser)
            ).build();
        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response register(@Valid RegisterDTO user) {
        Users validUser;
        String hash = pService.getHash(user.getPassword());

        if (user.getType() == Role.THERAPIST.getId()) {
            validUser = user.toTherapist(hash);
        } else if (user.getType() == Role.FAMILY.getId()) {
            validUser = user.toFamily(hash);
        } else {
            throw new NotFoundException();
        }

        validUser = repository.create(validUser);

        return Response.created(URI.create("/users/" + validUser.id)).header(
            "Authorization",
            jwtService.generateJwt(validUser)
        ).build();
    }
}
