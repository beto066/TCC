package br.unitins.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "families")
public class Family extends User {
    @ManyToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;
}
