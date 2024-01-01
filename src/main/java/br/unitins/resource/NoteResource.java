package br.unitins.resource;

import java.util.stream.Collectors;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.NoteTableDTO;
import br.unitins.dto.NoteTableResponseDTO;
import br.unitins.dto.NotepadDTO;
import br.unitins.dto.NotepadResponseDTO;
import br.unitins.model.Note;
import br.unitins.model.NoteTable;
import br.unitins.model.Notepad;
import br.unitins.model.Patient;
import br.unitins.model.enums.DifficultyLevel;
import br.unitins.model.enums.NoteType;
import br.unitins.model.enums.Program;
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
            throw new NotFoundException();
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
    @Path("/Pad")
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
}
