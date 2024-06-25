package br.unitins.model.enums;

public enum DifficultyLevel{
    MASS_TRAINING(1, "Mass training"),
    DISTRACT_PHASE(2, "Distract phase"),
    EXTENDED_TRAINING(3, "Extended training"),
    RANDOM_ROTATION(4, "Random rotation");

    private Integer id;
    private String label;

    DifficultyLevel(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static DifficultyLevel valueOf(Integer id) {
        DifficultyLevel[] values = DifficultyLevel.values();
        DifficultyLevel noteType = null;

        for (int i = 0; i < values.length && noteType == null; i++) {
            if (values[i].id == id) {
                noteType = values[i];
            }
        }

        return noteType;
    }

    public String getLabel() {
        return label;
    }

    public Integer getId() {
        return id;
    }
}
