package br.unitins.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.unitins.model.NoteTraining;

public class NoteTrainingResponseDTO extends NoteResponseDTO {
    List<String> results;

    public NoteTrainingResponseDTO(NoteTraining note) {
        super(note);
        results = note.results.stream().map(r -> r.id.result.getLabel()).collect(Collectors.toList());
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
