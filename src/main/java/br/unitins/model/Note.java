package br.unitins.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import br.unitins.model.enums.DifficultyLevel;
import br.unitins.model.enums.NoteType;
import br.unitins.model.enums.Program;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Note extends DefaultEntity {
    @ManyToOne
    @JoinColumn(name = "author_id")
    public Users author;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;

    @Enumerated(EnumType.ORDINAL)
    public Program program;

    @Enumerated(EnumType.ORDINAL)
    public NoteType type;

    @Enumerated(EnumType.ORDINAL)
    public DifficultyLevel level;
}
