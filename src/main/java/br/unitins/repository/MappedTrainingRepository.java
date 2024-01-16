package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.MappedTrainingResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTrainingRepository implements PanacheRepository<MappedTrainingResult>  {

}
