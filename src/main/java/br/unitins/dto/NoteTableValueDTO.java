package br.unitins.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.unitins.model.NoteTableValue;

public class NoteTableValueDTO {
    @Size(message = "O campo label deve ser menor ou igual a 2", max = 2)
    @NotBlank(message = "O campo label é obrigatório")
    private String label;

    @Size(message = "O campo value deve ser menor ou igual a 10", max = 10)
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
