package br.unitins.repository;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.NoResultException;

import br.unitins.dto.NoteTableValueResoponseDTO;
import br.unitins.model.NoteTableValue;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class NoteTableValueRepository implements PanacheRepository<NoteTableValue> {
    public List<NoteTableValueResoponseDTO> findAllValues() {
        return listAll().stream()
            .map(value -> new NoteTableValueResoponseDTO(value))
            .collect(Collectors.toList());
    }

    public List<NoteTableValueResoponseDTO> search(String search, Long id) {
        StringBuilder query = new StringBuilder();

        query.append("therapist_id = ?1 AND ");
        query.append("active = true AND ");
        query.append("(");
        query.append("  UPPER(value) LIKE ?2 OR ");
        query.append("  UPPER(label) LIKE ?2");
        query.append(")");

        return find(query.toString(), id, "%"+search.toUpperCase()+"%").stream()
            .map(value -> new NoteTableValueResoponseDTO(value))
            .collect(Collectors.toList());
    }

    public List<NoteTableValueResoponseDTO> findByTherapist(Long id) {
        return find("therapist_id = ?1 AND active = true", id).stream()
            .map(value -> new NoteTableValueResoponseDTO(value))
            .collect(Collectors.toList());
    }

    public NoteTableValueResoponseDTO findValue(Long id) {
        try {
            return new NoteTableValueResoponseDTO(find("id = ?1 AND active = true", id).singleResult());
        } catch (NoResultException $exception) {
            return null;
        }
    }

    public NoteTableValue findByIdAndTherapist(Long id, Long therapistId) {
        try {
            return find(
                "id = ?1 AND therapist_id = ?2 AND active = true",
                id,
                therapistId
            ).singleResult();
        } catch (NoResultException $exception) {
            return null;
        }
    }
}
