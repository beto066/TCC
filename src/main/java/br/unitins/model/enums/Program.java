package br.unitins.model.enums;

public enum Program {
    MATCHING("Matching"), RECEPTIVE("Receptive"), EXPRESSIVE("Expressive");

    private String label;

    Program(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
