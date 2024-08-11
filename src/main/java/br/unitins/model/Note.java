package br.unitins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import br.unitins.model.enums.DifficultyLevel;
import br.unitins.model.enums.NoteType;
import br.unitins.model.enums.Program;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Note extends DefaultEntity {
    public Boolean visibilityForFamily;

    @ManyToOne
    @JoinColumn(name = "author_id")
    public User author;

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
