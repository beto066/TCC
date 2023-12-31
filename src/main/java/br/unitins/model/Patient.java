package br.unitins.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Patient extends DefaultEntity {
    public String name;
    public LocalDate birth;
    public String imageName;

    @Column(name = "tratment_started_at")
    public LocalDate treatmentStartedAt;

    @OneToMany(mappedBy = "patient")
    public List<Family> family;

    @ManyToMany(mappedBy = "patients")
    public List<Therapist> therapists;

    @OneToMany(
        mappedBy = "patient",
        cascade = CascadeType.ALL
    )
    public List<Note> notes;
}
