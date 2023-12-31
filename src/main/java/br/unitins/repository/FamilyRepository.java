package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.Family;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class FamilyRepository implements PanacheRepository<Family> {

}
