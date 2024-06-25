package br.unitins.model.enums;

import java.util.ArrayList;
import java.util.List;

import br.unitins.model.NoteTableValue;
import br.unitins.model.Therapist;

public enum TrainingResult {
    SUCCESS(1, "SC", "Success"),
    SUCCESS_WITH_TIP(2, "ST", "Success with tip"),
    ERROR(3, "ER", "Error");

    private Integer id;
    private String label;
    private String value;

    TrainingResult(Integer id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public static TrainingResult valueOf(Integer id) {
        TrainingResult[] values = TrainingResult.values();
        TrainingResult result = null;

        for (int i = 0; i < values.length && result == null; i++) {
            if (values[i].id == id) {
                result = values[i];
            }
        }

        return result;
    }

    public static List<NoteTableValue> toNoteTableValues(Therapist therapist) {
        List<NoteTableValue> list = new ArrayList<NoteTableValue>();
        TrainingResult[] values = TrainingResult.values();

        for (TrainingResult result : values) {
            NoteTableValue value = new NoteTableValue();

            value.label = result.label;
            value.value = result.value;
            value.therapist = therapist;

            list.add(value);
        }

        return list;
    }

    public String getLabel() {
        return label;
    }

    public Integer getId() {
        return id;
    }
}
