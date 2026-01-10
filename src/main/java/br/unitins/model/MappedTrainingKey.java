package br.unitins.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class MappedTrainingKey implements Serializable {
    @Column(name = "training_id")
    public Long trainingId;

    @Column(name = "position")
    public Integer position;
}
