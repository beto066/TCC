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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Note extends DefaultEntity {
    @ManyToOne
    @JoinColumn(name = "id_author")
    public Users author;

    @ManyToOne
    @JoinColumn(name = "id_patient")
    public Patient patient;

    @Enumerated(EnumType.ORDINAL)
    public NoteType programs;

    @Enumerated(EnumType.ORDINAL)
    public NoteType type;

    @Enumerated(EnumType.ORDINAL)
    public DifficultyLevel level;
}
