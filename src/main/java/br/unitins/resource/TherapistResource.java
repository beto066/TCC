package br.unitins.resource;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import br.unitins.dto.TherapistResumeResponseDTO;
import br.unitins.model.Patient;
import br.unitins.repository.PatientRepository;
import br.unitins.repository.TherapistRepository;
import br.unitins.service.utils.JwtService;

@Path("/therapists")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TherapistResource {
    @Inject
    JwtService jwtService;

    @Inject
    TherapistRepository repository;

    @GET
    @RolesAllowed({"Family", "Network_Admin"})
    @Path("/patients/{id}")
    public List<TherapistResumeResponseDTO> getByPatient(@PathParam("id") Long id) {
        PatientRepository pRepository = new PatientRepository();

        Patient patient = pRepository.findById(id);

        return patient.therapists.stream().map(
            t -> new TherapistResumeResponseDTO(t)
        ).collect(Collectors.toList());
    }
}
