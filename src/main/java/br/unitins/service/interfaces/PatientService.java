package br.unitins.service.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.PatientDTO;
import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.User;

public interface PatientService {
    List<PatientResponseDTO> getAll(Long therapistId);

    List<PatientResponseDTO> search(String search, Long therapistId);

    Long count(User user, LocalDateTime from, LocalDateTime to);

    List<NoteResponseDTO> listNotes(Long id);

    PatientResponseDTO store(PatientDTO dto, Long userId);

    void unlinkNetwork(Long id);

    void unlinkTherapist(Long therapistId, Long patientId);
}
