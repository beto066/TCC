package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.model.MappedTrainingResult;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class MappedTrainingRepository implements PanacheRepository<MappedTrainingResult>  {

}
