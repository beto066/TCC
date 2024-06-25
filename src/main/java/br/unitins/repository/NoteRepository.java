package br.unitins.repository;

import jakarta.enterprise.context.ApplicationScoped;

import br.unitins.model.Note;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class NoteRepository implements PanacheRepository<Note> {

}
