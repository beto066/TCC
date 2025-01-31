package br.unitins.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "note_trainings")
public class NoteTraining extends Note {
    @OneToMany(mappedBy = "training")
    public List<MappedTrainingResult> results;
}
