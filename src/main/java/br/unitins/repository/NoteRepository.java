package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.dto.NoteFilterDTO;
import br.unitins.model.Note;
import br.unitins.model.Patient;
import br.unitins.model.User;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class NoteRepository implements PanacheRepository<Note> {
    public List<Note> findByUser(User user) {
        return find("author = ?1 ORDER BY createdAt", user).stream()
            .collect(Collectors.toList());
    }

    public List<Note> searchByUser(User user, NoteFilterDTO dto) {
        StringBuilder query = new StringBuilder();

        query.append("author = ?1 AND ");

        if (dto.getTitle() != null) {
            query.append("title LIKE ?2 AND ");
        } else {
            query.append("?2 IS NULL AND ");
        }

        if (dto.getFrom() != null) {
            query.append("createdAt >= ?3 AND ");
        } else {
            query.append("?3 IS NULL AND ");
        }

        if (dto.getTo() != null) {
            query.append("createdAt <= ?4 AND ");
        } else {
            query.append("?4 IS NULL AND ");
        }

        if (dto.getType() != null) {
            query.append("type.id = ?5");
        } else {
            query.append("?5 IS NULL");
        }

        return find(
            query.toString(),
            user,
            dto.getTitle() != null? "%" + dto.getTitle() + "%": null,
            dto.getFrom(),
            dto.getTo(),
            dto.getType()
        ).stream().collect(Collectors.toList());
    }

    public List<Note> findByPatient(Patient patient) {
        return find("patient = ?1", patient).stream()
            .collect(Collectors.toList());
    }

    public List<Note> findByPatient(Patient patient, NoteFilterDTO dto) {
        StringBuilder query = new StringBuilder();

        query.append("patient = ?1 AND ");

        if (dto.getTitle() != null) {
            query.append("title LIKE ?2 AND ");
        } else {
            query.append("?2 IS NULL AND ");
        }

        if (dto.getFrom() != null) {
            query.append("createdAt >= ?3 AND ");
        } else {
            query.append("?3 IS NULL AND ");
        }

        if (dto.getTo() != null) {
            query.append("createdAt <= ?4 AND ");
        } else {
            query.append("?4 IS NULL AND ");
        }

        if (dto.getType() != null) {
            query.append("type.id = ?5");
        } else {
            query.append("?5 IS NULL");
        }

        return find(
            query.toString(),
            patient,
            dto.getTitle() != null? "%" + dto.getTitle() + "%": null,
            dto.getFrom(),
            dto.getTo(),
            dto.getType()
        ).stream().collect(Collectors.toList());
    }

    public Long count(User user, LocalDateTime from, LocalDateTime to) {
        StringBuilder query = new StringBuilder();

        query.append("author = ?1 AND ");

        if (from != null) {
            query.append("createdAt >= ?2 AND ");
        } else {
            query.append("?2 IS NULL AND ");
        }

        if (to != null) {
            query.append("createdAt <= ?3 ");
        } else {
            query.append("?3 IS NULL ");
        }

        return find(
            query.toString(),
            user,
            from,
            to
        ).count();
    }
}
