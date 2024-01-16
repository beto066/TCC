package br.unitins.dto;

import br.unitins.model.MappedTrainingKey;
import br.unitins.model.MappedTrainingResult;
import br.unitins.model.NoteTraining;
import br.unitins.model.enums.TrainingResult;
import br.unitins.repository.MappedTrainingRepository;

public class MappedTrainingResultDTO {
    private Long trainingId;
    private Integer resultId;
    private Integer position;

    public MappedTrainingResult toMappedTableValue() {
        MappedTrainingResult mapped = new MappedTrainingResult();
        mapped.position = position;
        mapped.id = new MappedTrainingKey();
        mapped.id.trainingId = trainingId;
        mapped.id.result = TrainingResult.valueOf(resultId);
        return mapped;
    }

    public MappedTrainingResult toMappedTableValue(NoteTraining note) {
        MappedTrainingRepository repository = new MappedTrainingRepository();
        MappedTrainingResult mapped = new MappedTrainingResult();
        mapped.position = position;
        mapped.training = note;
        mapped.id = new MappedTrainingKey();
        mapped.id.trainingId = trainingId;
        mapped.id.result = TrainingResult.valueOf(resultId);
        repository.persist(mapped);
        return mapped;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Integer getResultId() {
        return resultId;
    }

    public void setResultId(Integer resultId) {
        this.resultId = resultId;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}
