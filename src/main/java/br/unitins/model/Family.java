package br.unitins.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Family")
public class Family extends Users {
    @ManyToOne
    @JoinColumn(name = "patient_id")
    public Patient patient;
}
