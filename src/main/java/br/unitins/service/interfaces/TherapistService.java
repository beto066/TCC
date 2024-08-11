package br.unitins.service.interfaces;

import java.util.List;

import br.unitins.dto.TherapistResumeResponseDTO;

public interface TherapistService {
    List<TherapistResumeResponseDTO> getByPatient(Long idPatitent);
}
