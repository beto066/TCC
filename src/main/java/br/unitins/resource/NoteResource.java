package br.unitins.resource;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.NoteResumeDTO;
import br.unitins.dto.NoteTableDTO;
import br.unitins.dto.NoteTrainingDTO;
import br.unitins.dto.NotepadDTO;
import br.unitins.model.Note;
import br.unitins.service.interfaces.NoteService;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {
    @Inject
    private JsonWebToken token;

    @Inject
    private NoteService service;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Therapist", "Family"})
    public NoteResponseDTO findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @POST
    @Path("/tables")
    @RolesAllowed("Therapist")
    @Transactional
    public Response createNoteTable(NoteTableDTO dto) {
        service.createNoteTable(dto, token);

        return Response.status(Status.CREATED).entity(dto).build();
    }

    @POST
    @Path("/training")
    @RolesAllowed("Therapist")
    @Transactional
    public Response createNoteTraining(NoteTrainingDTO dto) {
        service.createNoteTraining(dto, token);

        return Response.status(Status.CREATED).entity(dto).build();
    }

    @POST
    @Path("/pad")
    @RolesAllowed({"Therapist", "Family"})
    @Transactional
    public Response createNotepad(@Valid NotepadDTO dto) {
        service.createNotepad(dto, token);

        return Response.status(Status.CREATED).entity(dto).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Therapist", "Family"})
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid NoteResumeDTO dto) {
        Note note = service.update(id, dto, token);

        return Response
            .status(Status.OK)
            .entity(note)
            .build();
    }
}
