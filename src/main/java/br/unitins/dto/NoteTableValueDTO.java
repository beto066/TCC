package br.unitins.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import br.unitins.model.NoteTableValue;

public class NoteTableValueDTO {
    @Max(message = "O campo label deve ser menor ou igual a 2", value = 2)
    @NotBlank(message = "O campo label é obrigatório")
    private String label;

    @Max(message = "O campo value deve ser menor ou igual a 10", value = 10)
    @NotBlank(message = "O campo value é obrigatório")
    private String value;

    public NoteTableValue toNoteTableValue() {
        NoteTableValue value = new NoteTableValue();

        value.label = this.label.toUpperCase();
        value.value = this.value;
        return value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
