package br.unitins.model.enums;

public enum Program {
    MATCHING(1, "Matching"),
    RECEPTIVE(2, "Receptive"),
    EXPRESSIVE(3, "Expressive");

    private Integer id;
    private String label;

    Program(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static Program valueOf(Integer id) {
        Program[] values = Program.values();
        Program program = null;

        for (int i = 0; i < values.length && program == null; i++) {
            if (values[i].id == id) {
                program = values[i];
            }
        }

        return program;
    }

    public String getLabel() {
        return label;
    }

    public Integer getId() {
        return id;
    }
}
