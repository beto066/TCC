package br.unitins.service.implementations;

import java.util.stream.Collectors;

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
import br.unitins.service.interfaces.NoteService;
import br.unitins.service.utils.JwtService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;

@ApplicationScoped
public class NoteServiceImpl implements NoteService {
    @Inject
    private NoteRepository repository;

    @Inject
    private JwtService jwtService;

    @Override
    public NoteResponseDTO findById(Long id) {
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

    @Override
    public NoteTableResponseDTO createNoteTable(NoteTableDTO dto, JsonWebToken token) {
        PatientRepository patRepository = new PatientRepository();
        TherapistRepository thRepository = new TherapistRepository();
        NoteTableValueRepository vRepository = new NoteTableValueRepository();
        Program program = Program.valueOf(dto.getProgram());

        if (program == null) {
            throw new NotFoundException();
        }

        DifficultyLevel level = DifficultyLevel.valueOf(dto.getLevel());

        if (level == null) {
            throw new NotFoundException();
        }

        Patient patient = patRepository.findById(dto.getPatientId());
        NoteTable note = new NoteTable();

        note.author = thRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = program;
        note.type = NoteType.NOTETABLE;
        note.level = level;

        repository.persist(note);

        note.values = dto.getValues().stream().map(
            v -> v.toMappedTableValue(vRepository, note)
        ).collect(Collectors.toList());

        return new NoteTableResponseDTO(note);
    }

    @Override
    public NoteTrainingResponseDTO createNoteTraining(NoteTrainingDTO dto, JsonWebToken token) {
        PatientRepository patRepository = new PatientRepository();
        TherapistRepository thRepository = new TherapistRepository();
        Program program = Program.valueOf(dto.getProgram());

        if (program == null) {
            throw new NotFoundException();
        }

        DifficultyLevel level = DifficultyLevel.valueOf(dto.getLevel());

        if (level == null) {
            throw new NotFoundException();
        }

        Patient patient = patRepository.findById(dto.getPatientId());
        NoteTraining note = new NoteTraining();

        note.author = thRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = program;
        note.type = NoteType.NOTETRAINING;
        note.level = level;

        repository.persist(note);

        note.results = dto.getResults().stream().map(
            v -> v.toMappedTableValue(note)
        ).collect(Collectors.toList());

        return new NoteTrainingResponseDTO(note);
    }

    @Override
    public NotepadResponseDTO createNotepad(NotepadDTO dto, JsonWebToken token) {
        PatientRepository patRepository = new PatientRepository();
        UserRepository uRepository = new UserRepository();
        Program program = Program.valueOf(dto.getProgram());

        if (program == null) {
            throw new NotFoundException();
        }

        DifficultyLevel level = DifficultyLevel.valueOf(dto.getLevel());

        if (level == null) {
            throw new NotFoundException();
        }

        Patient patient = patRepository.findById(dto.getPatientId());
        Notepad note = new Notepad();

        note.author = uRepository.findById(jwtService.getUserId(token));
        note.patient = patient;
        note.program = program;
        note.type = NoteType.NOTEPAD;
        note.level = level;
        note.title = dto.getTitle();
        note.body = dto.getBody();

        repository.persist(note);

        return new NotepadResponseDTO(note);
    }

    @Override
    public Note update(Long id, NoteResumeDTO dto, JsonWebToken token) {
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

        return note;
    }
}
