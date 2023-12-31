package br.unitins.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.PatientDTO;
import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.Family;
import br.unitins.model.Patient;
import br.unitins.model.Therapist;
import br.unitins.model.Users;
import br.unitins.model.enums.Role;
import br.unitins.repository.FamilyRepository;
import br.unitins.repository.PatientRepository;
import br.unitins.repository.TherapistRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.JwtService;

@Path("/patients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatientResource {
    @Inject
    private PatientRepository repository;

    @Inject
    private JsonWebToken token;

    @Inject
    JwtService jwtService;

    @GET
    @RolesAllowed("Therapist")
    public List<PatientResponseDTO> getAll() {
        System.out.println(jwtService.getUserId(token));
        TherapistRepository uRepository = new TherapistRepository();
        Therapist therapist = uRepository.findById(jwtService.getUserId(token));

        if (therapist.patients == null || therapist.patients.isEmpty()) {
            return new ArrayList<PatientResponseDTO>();
        }

        return therapist.patients.stream().map(
            patient -> new PatientResponseDTO(patient)
        ).collect(Collectors.toList());
    }

    @GET
    @Path("/search/{search}")
    @RolesAllowed("Therapist")
    public List<PatientResponseDTO> search(@PathParam("search") String search) {
        return repository.search(search, jwtService.getUserId(token));
    }

    @POST
    @RolesAllowed({"Therapist", "Family"})
    public PatientResponseDTO store(PatientDTO dto) {
        if (!dto.validate()) {
            throw new WebApplicationException(
                "Payload Error",
                422
            );
        }

        UserRepository uRepository = new UserRepository();

        Users user = uRepository.findById(jwtService.getUserId(token));
        Patient patient = dto.toPatient();

        if (user.roles.contains(Role.THERAPIST)) {
            patient.therapists = new ArrayList<Therapist>();
            patient.therapists.add((Therapist) user);
        } else {
            patient.family = new ArrayList<Family>();
            patient.family.add((Family) user);
        }

        repository.persist(patient);

        return new PatientResponseDTO(patient);
    }

    @DELETE
    @Path("/{id}/unlink")
    @RolesAllowed({"Therapist"})
    public Response unlink(@PathParam("id") Long id) {
        Patient patient = repository.findById(id);
        patient.therapists.removeIf(t -> (t.id == jwtService.getUserId(token)));

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @DELETE
    @Path("/therapist_netwok/unlink")
    @RolesAllowed({"Family", "Network_Admin"})
    public Response unlinkNetwork(@PathParam("id") Long id) {
        Patient patient = repository.findById(id);
        patient.therapists = new ArrayList<>();

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @DELETE
    @Path("/therapists/{id}/unlink")
    @RolesAllowed({"Family"})
    public Response unlinkTherapist(@PathParam("id") Long id) {
        FamilyRepository fRepository = new FamilyRepository();
        Family family = fRepository.findById(jwtService.getUserId(token));

        family.patient.therapists.removeIf(t -> (t.id == id));

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }
}
