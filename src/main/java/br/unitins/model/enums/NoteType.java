package br.unitins.model.enums;

public enum NoteType {
    NOTEPAD("Notepad"), NOTETABLE("table"), NOTETRAINING("training");

    private String label;

    NoteType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
