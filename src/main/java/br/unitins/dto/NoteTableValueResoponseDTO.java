package br.unitins.dto;

import br.unitins.model.NoteTableValue;

public class NoteTableValueResoponseDTO {
    private Long id;
    private String label;
    private String value;

    public NoteTableValueResoponseDTO(NoteTableValue value) {
        this.id = value.id;
        this.label = value.label;
        this.value = value.value;
    }

    public NoteTableValue toNoteTableValue() {
        NoteTableValue value = new NoteTableValue();

        value.id = this.id;
        value.label = this.label.toUpperCase();
        value.value = this.value;
        return value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
