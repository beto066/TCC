package br.unitins.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.unitins.dto.AuthUsuarioDTO;
import br.unitins.model.Usuario;
import br.unitins.repository.UsuarioRepository;
import br.unitins.service.JwtService;
import br.unitins.service.PasswordService;

@Path("/auth")
public class AuthResource {

    @Inject
    UsuarioRepository repository;
    
    @Inject
    PasswordService pService;

    @Inject
    JwtService jwtService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response login(AuthUsuarioDTO usuario) {

        String hash = pService.getHash(usuario.getSenha());

        Usuario usuarioValido = repository.findByEmailAndSenha(usuario.getEmail(), hash);

        if (usuarioValido == null)
        return Response.status(Status.NO_CONTENT).entity("Usuário não encontrado.").build();
    else 
        return Response.ok()
            .header("Authorization", jwtService.generateJwt(usuarioValido))
            .build();
    }
    
}
