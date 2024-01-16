package br.unitins.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.model.NoteTraining;
import br.unitins.model.enums.TrainingResult;

public class NoteTrainingResponseDTO extends NoteResponseDTO {
    List<TrainingResult> results;

    public NoteTrainingResponseDTO(NoteTraining note) {
        super(note);
        results = note.results.stream().map(r -> r.id.result).collect(Collectors.toList());
    }

    public List<TrainingResult> getResults() {
        return results;
    }

    public void setResults(List<TrainingResult> results) {
        this.results = results;
    }
}
