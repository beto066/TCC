package br.unitins.resource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.NoteTableResponseDTO;
import br.unitins.dto.NoteTrainingResponseDTO;
import br.unitins.dto.NotepadResponseDTO;
import br.unitins.dto.PatientDTO;
import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.Family;
import br.unitins.model.NoteTable;
import br.unitins.model.NoteTraining;
import br.unitins.model.Notepad;
import br.unitins.model.Patient;
import br.unitins.model.Therapist;
import br.unitins.model.Users;
import br.unitins.model.enums.NoteType;
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

    @GET
    @Path("{id}/notes")
    @RolesAllowed({"Therapist", "Family"})
    public List<NoteResponseDTO> listNotes(@PathParam("id") Long id) {
        return repository.findById(id).notes.stream().map(n -> {
            if (n.type.getId() == NoteType.NOTETABLE.getId()) {
                return new NoteTableResponseDTO((NoteTable) n);
            }
            if (n.type.getId() == NoteType.NOTEPAD.getId()) {
                return new NotepadResponseDTO((Notepad) n);
            }
            if (n.type.getId() == NoteType.NOTETRAINING.getId()) {
            return new NoteTrainingResponseDTO((NoteTraining) n);
            }
            throw new NotFoundException();
        }).collect(Collectors.toList());
    }

    @POST
    @RolesAllowed({"Therapist", "Family"})
    @Transactional
    public PatientResponseDTO store(@Valid PatientDTO dto) {
        UserRepository uRepository = new UserRepository();

        Users user = uRepository.findById(jwtService.getUserId(token));
        Patient patient = dto.toPatient();

        if (user.roles.contains(Role.THERAPIST)) {
            Therapist therapist = (Therapist) user;
            if (therapist.patients == null) {
                therapist.patients = new ArrayList<Patient>();
            }

            therapist.patients.add(patient);
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
    @Transactional
    public Response unlink(@PathParam("id") Long id) {
        TherapistRepository tRepository = new TherapistRepository();
        Therapist therapist = tRepository.findById(jwtService.getUserId(token));
        Patient patient = repository.findById(id);

        int index = therapist.patients.indexOf(patient);
        therapist.patients.remove(index);

        return Response
            .status(Status.NO_CONTENT)
            .entity("Unlinked succes")
            .build();
    }

    @DELETE
    @Path("/therapist_netwok/unlink")
    @RolesAllowed({"Family", "Network_Admin"})
    @Transactional
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
    @Transactional
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
