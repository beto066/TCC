package br.unitins.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import br.unitins.dto.PatientResponseDTO;
import br.unitins.model.Family;
import br.unitins.model.Patient;
import br.unitins.model.Therapist;
import br.unitins.model.User;
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

    public Long count(Therapist user, LocalDateTime from, LocalDateTime to) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT p.id FROM");
        query.append("  Patient p ");
        query.append("JOIN ");
        query.append("  p.therapists t ");
        query.append("WHERE ");
        query.append("  t = ?1 AND ");

        if (from != null) {
            query.append("  p.createdAt >= ?2 AND ");
        } else {
            query.append("  ?2 IS NULL AND ");
        }

        if (to != null) {
            query.append("  p.createdAt <= ?3 ");
        } else {
            query.append("  ?3 IS NULL ");
        }

        return find(
            query.toString(),
            user,
            from,
            to
        ).count();
    }

    public Long count(Family user, LocalDateTime from, LocalDateTime to) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT p.id FROM");
        query.append("  Patient p ");
        query.append("JOIN ");
        query.append("  p.family f ");
        query.append("WHERE ");
        query.append("  f = ?1 AND ");

        if (from != null) {
            query.append("  p.createdAt >= ?2 AND ");
        } else {
            query.append("  ?2 IS NULL AND ");
        }
        if (to != null) {
            query.append("  p.createdAt <= ?3 ");
        } else {
            query.append("  ?3 IS NULL ");
        }

        return find(
            query.toString(),
            user,
            from,
            to
        ).count();
    }

    public Long count(User user, LocalDateTime from, LocalDateTime to) throws NotFoundException {
        throw new NotFoundException();
    }
}
