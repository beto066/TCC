package br.unitins.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class NoteResumeDTO {
    @Valid
    @Min(message = "Campo title não pode ser menor que 2", value = 2)
    @Max(message = "Campo title não pode ser menor que 20", value = 30)
    private ArrayList<String> body;
    private List<MappedTableValueDTO> values;
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

    public Boolean getVisibilityForFamily() {
        return visibilityForFamily;
    }

    public void setVisibilityForFamily(Boolean visibilityForFamily) {
        this.visibilityForFamily = visibilityForFamily;
    }
}
