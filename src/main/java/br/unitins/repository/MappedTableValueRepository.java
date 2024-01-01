package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.MappedTableValue;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTableValueRepository implements PanacheRepository<MappedTableValue> {

}
