package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.Therapist;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class TherapistRepository implements PanacheRepository<Therapist> {
}
