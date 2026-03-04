package br.unitins.model;

import br.unitins.model.enums.TrainingResult;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "mapped_training_results")
public class MappedTrainingResult extends DefaultEntity {
    @Column(name = "position")
    public Integer position;

    @ManyToOne
    @JoinColumn(name = "training_id")
    public NoteTraining training;

    @Column(name = "result")
    @Enumerated(EnumType.ORDINAL)
    public TrainingResult result;
}
