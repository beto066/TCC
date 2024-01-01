package br.unitins.model.enums;

public enum NoteType {
    NOTEPAD(1, "Notepad"),
    NOTETABLE(2, "table"),
    NOTETRAINING(3 ,"training");

    private Integer id;
    private String label;

    NoteType(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static NoteType valueOf(Integer id) {
        NoteType[] values = NoteType.values();
        NoteType noteType = null;

        for (int i = 0; i < values.length && noteType == null; i++) {
            if (values[i].id == id) {
                noteType = values[i];
            }
        }

        return noteType;
    }

    public Integer getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }
}
