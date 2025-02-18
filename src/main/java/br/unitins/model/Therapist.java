package br.unitins.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "therapists")
public class Therapist extends User {
    @ManyToMany
    @JoinTable(
        name = "therapist_patients",
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
