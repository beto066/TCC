package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;
import br.unitins.model.MappedTableValue;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTableValueRepository implements PanacheRepository<MappedTableValue> {
    public boolean isPresent(Integer position, Long noteId) {
        return find(
            "position = ?1 AND table.id = ?2",
            position,
            noteId
        ).count() > 0;
    }
}
