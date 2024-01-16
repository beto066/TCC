package br.unitins.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

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
