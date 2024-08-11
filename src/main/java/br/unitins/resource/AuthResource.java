package br.unitins.resource;

import java.net.URI;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import br.unitins.dto.AuthDTO;
import br.unitins.dto.RegisterDTO;
import br.unitins.model.User;
import br.unitins.service.interfaces.AuthService;
import br.unitins.service.utils.JwtService;
@Path("/auth")
public class AuthResource {

    @Inject
    private AuthService service;

    @Inject
    private JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(@Valid AuthDTO user) {
        User validUser = service.login(user);

        if (validUser == null){
            return Response
                .status(Status.NO_CONTENT)
                .entity("Usuário não encontrado.")
                .build();
        }

        return Response.noContent().header(
            "Authorization",
            jwtService.generateJwt(validUser)
        ).build();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response register(@Valid RegisterDTO user) {
        User validUser = service.register(user);

        return Response.created(URI.create("/users/" + validUser.id)).header(
            "Authorization",
            jwtService.generateJwt(validUser)
        ).build();
    }
}
