package br.unitins.model;

public enum Role {
    ADMIN("Admin"), USER("User");

    private String label;

    Role(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
