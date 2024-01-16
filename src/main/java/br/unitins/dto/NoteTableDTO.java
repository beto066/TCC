package br.unitins.dto;

import java.util.ArrayList;
import java.util.List;

public class NoteTableDTO {
    private Long patientId;
    private Integer program;
    private Integer level;
    private Boolean visibilityForFamily;
    private List<MappedTableValueDTO> values;

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Integer getProgram() {
        return program;
    }

    public void setProgram(Integer program) {
        this.program = program;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getVisibilityForFamily() {
        return visibilityForFamily;
    }

    public void setVisibilityForFamily(Boolean visibilityForFamily) {
        this.visibilityForFamily = visibilityForFamily;
    }

    public List<MappedTableValueDTO> getValues() {
        if (values == null) {
            values = new ArrayList<>();
        }
        return values;
    }

    public void setValues(List<MappedTableValueDTO> values) {
        this.values = values;
    }
}
