package br.unitins.resource;

import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.NoteResumeDTO;
import br.unitins.dto.NoteTableDTO;
import br.unitins.dto.NoteTableResponseDTO;
import br.unitins.dto.NoteTrainingDTO;
import br.unitins.dto.NoteTrainingResponseDTO;
import br.unitins.dto.NotepadDTO;
import br.unitins.dto.NotepadResponseDTO;
import br.unitins.model.Note;
import br.unitins.model.NoteTable;
import br.unitins.model.NoteTraining;
import br.unitins.model.Notepad;
import br.unitins.model.Patient;
import br.unitins.model.enums.DifficultyLevel;
import br.unitins.model.enums.NoteType;
import br.unitins.model.enums.Program;
import br.unitins.model.enums.Role;
import br.unitins.repository.NoteRepository;
import br.unitins.repository.NoteTableValueRepository;
import br.unitins.repository.PatientRepository;
import br.unitins.repository.TherapistRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.JwtService;

@Path("/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NoteResource {
    @Inject
    private NoteRepository repository;

    @Inject
    private JsonWebToken token;

    @Inject
    JwtService jwtService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"Therapist", "Family"})
    public NoteResponseDTO findById(@PathParam("id") Long id) {
        Note note = repository.findById(id);

        if (note.type.getId() == NoteType.NOTETABLE.getId()) {
            return new NoteTableResponseDTO((NoteTable) note);
        }
        if (note.type.getId() == NoteType.NOTEPAD.getId()) {
            return new NotepadResponseDTO((Notepad) note);
        }
        if (note.type.getId() == NoteType.NOTETRAINING.getId()) {
            return new NoteTrainingResponseDTO((NoteTraining) note);
        }

        throw new NotFoundException();
    }

    @POST
    @Path("/tables")
    @RolesAllowed("Therapist")
    @Transactional
    public NoteTableResponseDTO createNoteTable(NoteTableDTO dto) {
        PatientRepository patRepository = new PatientRepository();
        TherapistRepository thRepository = new TherapistRepository();
        NoteTableValueRepository vRepository = new NoteTableValueRepository();

        Patient patient = patRepository.findById(dto.getPatientId());
        NoteTable note = new NoteTable();

        note.author = thRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = Program.valueOf(dto.getProgram());
        note.type = NoteType.NOTETABLE;
        note.level = DifficultyLevel.valueOf(dto.getLevel());

        repository.persist(note);

        note.values = dto.getValues().stream().map(
            v -> v.toMappedTableValue(vRepository, note)
        ).collect(Collectors.toList());

        return new NoteTableResponseDTO(note);
    }

    @POST
    @Path("/training")
    @RolesAllowed("Therapist")
    @Transactional
    public NoteTrainingResponseDTO createNoteTraining(NoteTrainingDTO dto) {
        PatientRepository patRepository = new PatientRepository();
        TherapistRepository thRepository = new TherapistRepository();

        Patient patient = patRepository.findById(dto.getPatientId());
        NoteTraining note = new NoteTraining();

        note.author = thRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = Program.valueOf(dto.getProgram());
        note.type = NoteType.NOTETRAINING;
        note.level = DifficultyLevel.valueOf(dto.getLevel());

        repository.persist(note);

        note.results = dto.getResults().stream().map(
            v -> v.toMappedTableValue(note)
        ).collect(Collectors.toList());

        return new NoteTrainingResponseDTO(note);
    }

    @POST
    @Path("/pad")
    @RolesAllowed({"Therapist", "Family"})
    @Transactional
    public NotepadResponseDTO createNotepad(NotepadDTO dto) {
        PatientRepository patRepository = new PatientRepository();
        UserRepository uRepository = new UserRepository();

        Patient patient = patRepository.findById(dto.getPatientId());
        Notepad note = new Notepad();

        note.author = uRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = Program.valueOf(dto.getProgram());
        note.type = NoteType.NOTEPAD;
        note.level = DifficultyLevel.valueOf(dto.getLevel());
        note.title = dto.getTitle();
        note.body = dto.getBody();

        repository.persist(note);

        return new NotepadResponseDTO(note);
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"Therapist", "Family"})
    @Transactional
    public Response update(@PathParam("id") Long id, NoteResumeDTO dto) {
        NoteTableValueRepository vRepository = new NoteTableValueRepository();

        Note note = repository.findById(id);

        if (note.author.id != jwtService.getUserId(token)) {
            throw new WebApplicationException(
                "You can't edit a note you didn't create",
                422
            );
        }

        if (!note.author.roles.contains(Role.FAMILY)) {
            note.visibilityForFamily = dto.getVisibilityForFamily();
        }

        if (note.type == NoteType.NOTEPAD) {
            Notepad pad = (Notepad) note;

            pad.body = dto.getBody();
        } else if (note.type == NoteType.NOTETABLE) {
            NoteTable table = (NoteTable) note;

            table.values = dto.getValues().stream().map(
                v -> v.toMappedTableValue(vRepository, table)
            ).collect(Collectors.toList());
        }

        return Response
            .status(Status.NO_CONTENT)
            .build();
    }
}
