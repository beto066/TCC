package br.unitins.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "Therapist")
public class Therapist extends Users {
    @ManyToMany
    @JoinTable(
        name = "therapist_patient",
        joinColumns = @JoinColumn(name = "therapist_id"),
        inverseJoinColumns = @JoinColumn(name = "patient_id")
    )
    public List<Patient> patients;

    @OneToMany(
        mappedBy = "therapist",
        cascade = CascadeType.ALL
    )
    public List<NoteTableValue> tableValues;
}
