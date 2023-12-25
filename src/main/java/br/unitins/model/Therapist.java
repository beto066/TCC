package br.unitins.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Therapist")
public class Therapist extends Users {
    public String patient;
}
