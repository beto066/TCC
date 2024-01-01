package br.unitins.repository;

import javax.enterprise.context.ApplicationScoped;

import br.unitins.model.Note;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class NoteRepository implements PanacheRepository<Note> {

}
