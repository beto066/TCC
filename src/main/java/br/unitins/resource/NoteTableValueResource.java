package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteTableValueDTO;
import br.unitins.dto.NoteTableValueResoponseDTO;
import br.unitins.model.NoteTableValue;
import br.unitins.model.Therapist;
import br.unitins.repository.NoteTableValueRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.JwtService;

@Path("/users/notes/values")
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
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> getAll() {
        Long therapist = jwtService.getUserId(token);
        return repository.findByTherapist(therapist);
    }

    @GET
    @Path("/search/{search}")
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> search(@PathParam("search") String search) {
        Long therapist = jwtService.getUserId(token);
        return repository.search(search, therapist);
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
    @Transactional
    public NoteTableValueResoponseDTO store(@Valid NoteTableValueDTO dto) {
        UserRepository uRepository = new UserRepository();
        NoteTableValue value = dto.toNoteTableValue();
        value.therapist = (Therapist) uRepository.findById(jwtService.getUserId(token));
        repository.persist(value);

        return new NoteTableValueResoponseDTO(value);
    }

    @DELETE
    @RolesAllowed("Therapist")
    @Path("/{id}")
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
