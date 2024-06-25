package br.unitins.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class MappedTrainingResult {
    @EmbeddedId
    public MappedTrainingKey id;

    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    public NoteTraining training;

    // @MapsId("result")
    // @Column(name = "result")
    // public TrainingResult result;

    public Integer position;
}
