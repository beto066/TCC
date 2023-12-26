package br.unitins.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class NoteTableValue extends DefaultEntity {
    public String label;
    public String value;

    @ManyToOne
    @JoinColumn(name = "id_therapist")
    public Therapist therapist;

    @ManyToOne
    @JoinColumn(name = "id_table")
    public Therapist table;

    @OneToMany(mappedBy = "value")
    public List<MappedTableValue> notes;
}
