package br.unitins.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Therapist")
public class Therapist extends Users {
    public String patient;

    @OneToMany(
        mappedBy = "author",
        cascade = CascadeType.ALL
    )
    public List<Note> notes;

    @OneToMany(
        mappedBy = "therapist",
        cascade = CascadeType.ALL
    )
    public List<NoteTableValue> tableValues;
}
