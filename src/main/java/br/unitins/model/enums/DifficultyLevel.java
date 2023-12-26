package br.unitins.model.enums;

public enum DifficultyLevel{
    MASS_TRAINING("Mass training"),
    DISTRACT_PHASE("Distract phase"),
    EXTENDED_TRAINING("Extended trainig"),
    RANDOM_ROTATION("Random Rotation");

    private String label;

    DifficultyLevel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
