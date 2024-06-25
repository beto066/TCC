package br.unitins.repository;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.Patient;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PatientRepository implements PanacheRepository<Patient> {
    public  List<PatientResponseDTO> findAllPatient() {
        return listAll().stream()
            .map(patient -> new PatientResponseDTO(patient))
            .collect(Collectors.toList());
    }

    public  List<PatientResponseDTO> search(String name, Long therapistId) {
        StringBuilder query = new StringBuilder();

        query.append("SELECT p FROM  ");
        query.append("  Patient p ");
        query.append("JOIN ");
        query.append("  p.therapists tp ");
        query.append("WHERE ");
        query.append("  lower(p.name) LIKE ?1 AND ");
        query.append("  tp.id = ?2 ");

        return find(query.toString(), "%"+name.toLowerCase()+"%", therapistId).stream()
            .map(patient -> new PatientResponseDTO(patient))
            .collect(Collectors.toList());
    }

    public List<PatientResponseDTO> findByTherapist(Long id) {
        return find("therapist_id = ?1 AND active = true", id).stream()
            .map(value -> new PatientResponseDTO(value))
            .collect(Collectors.toList());
    }
}
