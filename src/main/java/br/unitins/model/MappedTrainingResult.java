package br.unitins.model;

import br.unitins.model.enums.TrainingResult;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "mapped_training_results")
public class MappedTrainingResult {
    @EmbeddedId
    public MappedTrainingKey id;

    @ManyToOne
    @MapsId("trainingId")
    @JoinColumn(name = "training_id")
    public NoteTraining training;

    @MapsId("result")
    @Column(name = "result")
    @Enumerated(EnumType.ORDINAL)
    public TrainingResult result;
}
