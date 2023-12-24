package br.unitins.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unitins.dto.AuthDTO;
import br.unitins.model.Users;
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
    public Response login(AuthDTO user) {
        String hash = pService.getHash(user.getPassword());

        Users validUser = repository.findByEmailAndPassword(user.getEmail(), hash);

        if (validUser == null){
            return Response.status(Status.NO_CONTENT).entity("Usuário não encontrado.").build();
        } else {
            return Response.ok()
                .header("Authorization", jwtService.generateJwt(validUser))
                .build();
        }
    }
}
