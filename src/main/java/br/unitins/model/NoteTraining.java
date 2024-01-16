package br.unitins.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "NoteTraining")
public class NoteTraining extends Note {
    @OneToMany(mappedBy = "training")
    public List<MappedTrainingResult> results;
}
