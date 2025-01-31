package br.unitins.model.enums;

public enum Role {
    THERAPIST(1, "Therapist"),
    FAMILY(2, "Family"),
    NETWORK_ADMIN(3, "Network_Admin");

    private Integer id;
    private String label;

    Role(Integer id, String label) {
        this.id = id;
        this.label = label;
    }

    public static Role valueOfRole(String label) {
        Role[] values = Role.values();
        Role role = null;

        for (int i = 0; i < values.length && role == null; i++) {
            if (values[i].label == label) {
                role = values[i];
            }
        }

        return role;
    }

    public static Role valueOf(Integer id) {
        Role[] values = Role.values();
        Role role = null;

        for (int i = 0; i < values.length && role == null; i++) {
            if (values[i].id == id) {
                role = values[i];
            }
        }

        return role;
    }

    public String getLabel() {
        return label;
    }

    public Integer getId() {
        return id;
    }
}
