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

    public MappedTrainingResult toMappedTrainingResult() {
        MappedTrainingResult mapped = new MappedTrainingResult();
        mapped.id = new MappedTrainingKey();
        mapped.id.trainingId = trainingId;
        mapped.result = TrainingResult.valueOf(resultId);
        mapped.id.position = position;
        return mapped;
    }

    public MappedTrainingResult toMappedTrainingResult(NoteTraining note) {
        MappedTrainingRepository repository = new MappedTrainingRepository();

        MappedTrainingKey id = new MappedTrainingKey();
        id.trainingId = trainingId;
        id.position = position;

        MappedTrainingResult mapped = repository.find(id);

        if (mapped == null) {
            mapped = new MappedTrainingResult();
            mapped.training = note;
            mapped.result = TrainingResult.valueOf(resultId);
            repository.persist(mapped);
        }

        mapped.training = note;
        mapped.result = TrainingResult.valueOf(resultId);

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
