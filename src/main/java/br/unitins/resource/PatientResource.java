package br.unitins.resource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.PatientDTO;
import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.User;
import br.unitins.service.interfaces.PatientService;
import br.unitins.service.utils.JwtService;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    private PatientService service;

    @Inject
    private JsonWebToken token;

    @Inject
    JwtService jwtService;

    @GET
    @RolesAllowed("Therapist")
    public List<PatientResponseDTO> getAll() {
        Long therapistId = jwtService.getUserId(token);
        return service.getAll(therapistId);
    }

    @GET
    @Path("/search/{search}")
    @RolesAllowed("Therapist")
    public List<PatientResponseDTO> search(@PathParam("search") String search) {
        Long therapistId = jwtService.getUserId(token);
        return service.search(search, therapistId);
    }

    @GET
    @Path("/count")
    @RolesAllowed({"Therapist", "Family"})
    public Long count(
        @QueryParam("from") String from,
        @QueryParam("to") String to
    ) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");

        User user = jwtService.getUser(token);

        LocalDateTime dateTimeFrom = null;
        LocalDateTime dateTimeTo = null;

        if (from != null && !from.isEmpty()) {
            dateTimeFrom = LocalDateTime.parse(from, formatter);
        }

        if (to != null && !to.isEmpty()) {
            dateTimeTo = LocalDateTime.parse(to, formatter);
        }

        return service.count(user, dateTimeFrom, dateTimeTo);
    }

    @GET
    @Path("/{id}/notes")
    @RolesAllowed({"Therapist", "Family"})
    public List<NoteResponseDTO> listNotes(@PathParam("id") Long id) {
        return service.listNotes(id);
    }

    @POST
    @RolesAllowed({"Therapist", "Family"})
    public Response store(@Valid PatientDTO dto) {
        Long userId = jwtService.getUserId(token);
        PatientResponseDTO patientResponse = service.store(dto, userId);

        return Response
            .status(Status.CREATED)
            .entity(patientResponse)
            .build();
    }

    @DELETE
    @Path("/{id}/unlink")
    @RolesAllowed({"Therapist"})
    public Response unlink(@PathParam("id") Long id) {
        Long therapistId = jwtService.getUserId(token);

        service.unlinkTherapist(therapistId, id);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @DELETE
    @Path("/therapist_netwok/unlink")
    @RolesAllowed({"Family", "Network_Admin"})
    public Response unlinkNetwork(@PathParam("id") Long id) {
        service.unlinkNetwork(id);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @DELETE
    @Path("/{patientId}/therapists/{therapistId}/unlink")
    @RolesAllowed({"Family"})
    public Response unlinkTherapist(
        @PathParam("patientId") Long patientId,
        @PathParam("therapistId") Long therapistId
    ) {
        service.unlinkTherapist(patientId, therapistId);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }
}
