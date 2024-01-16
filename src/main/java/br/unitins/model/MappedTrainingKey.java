package br.unitins.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.unitins.model.enums.TrainingResult;

public class MappedTrainingKey implements Serializable {
    @Column(name = "training_id")
    public Long trainingId;

    @Column(name = "result")
    @Enumerated(EnumType.ORDINAL)
    public TrainingResult result;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((trainingId == null) ? 0 : trainingId.hashCode());
        result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MappedTrainingKey other = (MappedTrainingKey) obj;
        if (trainingId == null) {
            if (other.trainingId != null)
                return false;
        } else if (!trainingId.equals(other.trainingId))
            return false;
        if (result == null) {
            if (other.result != null)
                return false;
        } else if (!result.equals(other.result))
            return false;
        return true;
    }
}
