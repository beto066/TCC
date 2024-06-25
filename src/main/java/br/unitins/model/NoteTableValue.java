package br.unitins.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class NoteTableValue extends DefaultEntity {
    public String label;
    public String value;

    public Boolean active = true;

    @ManyToOne
    @JoinColumn(name = "therapist_id")
    public Therapist therapist;

    @OneToMany(mappedBy = "value")
    public List<MappedTableValue> notes;
}
