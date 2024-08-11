package br.unitins.service.implementations;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.TherapistResumeResponseDTO;
import br.unitins.model.Patient;
import br.unitins.repository.PatientRepository;
import br.unitins.service.interfaces.TherapistService;
import jakarta.inject.Inject;

public class TherapistServiceImpl implements TherapistService {
    @Inject
    PatientRepository patientRepository;

    @Override
    public List<TherapistResumeResponseDTO> getByPatient(Long idPatitent) {
        Patient patient = patientRepository.findById(idPatitent);

        return patient.therapists.stream().map(
            t -> new TherapistResumeResponseDTO(t)
        ).collect(Collectors.toList());
    }
}
