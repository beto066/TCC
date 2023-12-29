package br.unitins.dto;

import javax.ws.rs.WebApplicationException;

import br.unitins.model.NoteTableValue;

public class NoteTableValueDTO {
    private String label;
    private String value;

    public NoteTableValue toNoteTableValue() {
        NoteTableValue value = new NoteTableValue();

        value.label = this.label.toUpperCase();
        value.value = this.value;
        return value;
    }

    public boolean validate() {
        boolean result = true;
        if (label == null || label.trim().length() > 2 || label.trim().isEmpty()) {
            throw new WebApplicationException(
                "The field label is required with a size less than 2",
                422
            );
        }

        if (value == null || value.trim().length() > 10 || value.trim().isEmpty()) {
            throw new WebApplicationException(
                "The field label is required with a size less than 10",
                422
            );
        }

        return result;
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
