package br.unitins.resource;

import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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
import br.unitins.service.interfaces.NoteTableValueService;
import br.unitins.service.utils.JwtService;

@Path("/users/notes/values")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteTableValueResource {
    @Inject
    private JsonWebToken token;

    @Inject
    JwtService jwtService;

    @Inject
    NoteTableValueService service;

    @GET
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> getAll() {
        Long therapistId = jwtService.getUserId(token);
        return service.getAll(therapistId);
    }

    @GET
    @Path("/search/{search}")
    @RolesAllowed("Therapist")
    public List<NoteTableValueResoponseDTO> search(@PathParam("search") String search) {
        Long therapistId = jwtService.getUserId(token);
        return service.search(search, therapistId);
    }

    @GET
    @Path("/{id}")
    @RolesAllowed("Therapist")
    public NoteTableValueResoponseDTO find(@PathParam("id") Long id) throws NotFoundException {
        return service.find(id);
    }

    @POST
    @RolesAllowed("Therapist")
    public Response store(@Valid NoteTableValueDTO dto) {
        Long therapistId = jwtService.getUserId(token);
        NoteTableValueResoponseDTO noteTableValue = service.store(therapistId, dto);

        return Response
            .status(Status.CREATED)
            .entity(noteTableValue)
            .build();
    }

    @DELETE
    @RolesAllowed("Therapist")
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        Long therapistId = jwtService.getUserId(token);
        service.delete(therapistId, id);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Deletado com sucesso")
            .build();
    }
}
