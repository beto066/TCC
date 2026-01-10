package br.unitins.dto;

import java.util.ArrayList;
import java.util.List;

public class NoteResumeDTO {
    private ArrayList<String> body;
    private List<MappedTableValueDTO> values;
    private List<MappedTrainingResultDTO> results;
    private Boolean visibilityForFamily;

    public ArrayList<String> getBody() {
        return body;
    }

    public void setBody(ArrayList<String> body) {
        this.body = body;
    }

    public List<MappedTableValueDTO> getValues() {
        return values;
    }

    public void setValues(List<MappedTableValueDTO> values) {
        this.values = values;
    }

    public List<MappedTrainingResultDTO> getResults() {
        return results;
    }

    public void setResults(List<MappedTrainingResultDTO> results) {
        this.results = results;
    }

    public Boolean getVisibilityForFamily() {
        return visibilityForFamily;
    }

    public void setVisibilityForFamily(Boolean visibilityForFamily) {
        this.visibilityForFamily = visibilityForFamily;
    }
}
