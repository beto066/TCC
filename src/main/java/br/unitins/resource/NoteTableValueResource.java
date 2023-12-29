package br.unitins.resource;

import java.net.URI;
import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteTableValueDTO;
import br.unitins.dto.NoteTableValueResoponseDTO;
import br.unitins.model.NoteTableValue;
import br.unitins.model.Therapist;
import br.unitins.repository.NoteTableValueRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.JwtService;

@Path("/users/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteTableValueResource {
    @Inject
    private NoteTableValueRepository repository;

    @Inject
    private JsonWebToken token;

    @Inject
    JwtService jwtService;

    @GET
    @Path("/search/{search}")
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> search(@PathParam("search") String search) {
        Long therapist = jwtService.getUserId(token);
        return repository.search(search, therapist);
    }

    @GET
    @Path("/")
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> getAll(@PathParam("hello") String hello) {
        System.out.println(hello);
        Long therapist = jwtService.getUserId(token);
        return repository.findByTherapist(therapist);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Therapist")
    public NoteTableValueResoponseDTO find(@PathParam("id") Long id) {
        NoteTableValueResoponseDTO value = repository.findValue(id);

        if (value == null) {
            throw new NotFoundException();
        }

        return value;
    }

    @POST
    @RolesAllowed("Therapist")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response store(NoteTableValueDTO dto) {
        if (!dto.validate()) {
            throw new WebApplicationException(
                "Payload Error",
                422
            );
        }

        UserRepository uRepository = new UserRepository();
        NoteTableValue value = dto.toNoteTableValue();
        value.therapist = (Therapist) uRepository.findById(jwtService.getUserId(token));
        repository.persist(value);

        return Response.created(URI.create("/users/notes/" + value.id)).build();
    }

    @DELETE
    @RolesAllowed("Therapist")
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Long therapistId = jwtService.getUserId(token);

        NoteTableValue value = repository.findByIdAndTherapist(id, therapistId);
        value.active = false;

        return Response
            .status(Status.NO_CONTENT)
            .entity("Deletado com sucesso")
            .build();
    }
}
