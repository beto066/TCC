package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;
import br.unitins.model.MappedTableKey;
import br.unitins.model.MappedTableValue;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTableValueRepository implements PanacheRepository<MappedTableValue> {
    public boolean isPresent(MappedTableKey serializable) {
        return find(
            "id.tableId = ?1 AND id.position = ?2",
            serializable.tableId,
            serializable.position
        ).count() > 0;
    }
}
