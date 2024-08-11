package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.dto.NoteResponseDTO;
import br.unitins.dto.PatientDTO;
import br.unitins.dto.PatientResponseDTO;

public interface PatientService {
    List<PatientResponseDTO> getAll(Long therapistId);

    List<PatientResponseDTO> search(String search, Long therapistId);

    List<NoteResponseDTO> listNotes(Long id);

    PatientResponseDTO store(PatientDTO dto, Long userId);

    void unlinkNetwork(Long id);

    void unlinkTherapist(Long therapistId, Long patientId);
}
