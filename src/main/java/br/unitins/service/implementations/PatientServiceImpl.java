package br.unitins.service.implementations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import br.unitins.model.User;
import br.unitins.model.enums.NoteType;
import br.unitins.model.enums.Role;
import br.unitins.repository.PatientRepository;
import br.unitins.repository.TherapistRepository;
import br.unitins.repository.UserRepository;
import br.unitins.service.interfaces.PatientService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PatientServiceImpl implements PatientService {
    @Inject UserRepository userRepository;

    @Inject
    TherapistRepository therapistRepository;

    @Inject
    private PatientRepository patientRepository;

    @Override
    public List<PatientResponseDTO> getAll(Long therapistId) {
        Therapist therapist = therapistRepository.findById(therapistId);

        if (therapist.patients == null || therapist.patients.isEmpty()) {
            return new ArrayList<PatientResponseDTO>();
        }

        return therapist.patients.stream().map(
            patient -> new PatientResponseDTO(patient)
        ).collect(Collectors.toList());
    }

    @Override
    public List<PatientResponseDTO> search(String search, Long therapistId) {
        return patientRepository.search(search, therapistId);
    }

    @Override
    public Long count(User user, LocalDateTime from, LocalDateTime to) {
        if (user instanceof Therapist) {
            return patientRepository.count((Therapist) user, from, to);
        }
        if (user instanceof Family) {
            return patientRepository.count((Family) user, from, to);
        }

        return 0l;
    }

    @Override
    public List<NoteResponseDTO> listNotes(Long id) {
        return patientRepository.findById(id).notes.stream().map(n -> {
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

    @Override
    @Transactional
    public PatientResponseDTO store(PatientDTO dto, Long userId) {
        User user = userRepository.findById(userId);
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

        patientRepository.persist(patient);

        return new PatientResponseDTO(patient);
    }

    @Override
    @Transactional
    public void unlinkNetwork(Long id) {
        Patient patient = patientRepository.findById(id);
        patient.therapists = new ArrayList<>();
    }

    @Override
    @Transactional
    public void unlinkTherapist(Long therapistId, Long patientId) {
        Therapist therapist = therapistRepository.findById(therapistId);
        Patient patient = patientRepository.findById(patientId);

        int index = therapist.patients.indexOf(patient);
        therapist.patients.remove(index);
    }
}
