package br.unitins.dto;

import java.util.List;

public class NoteTrainingDTO {
    private Long patientId;
    private Integer program;
    private Integer level;
    private Boolean visibilityForFamily;
    private List<MappedTrainingResultDTO> results;

    public Long getPatientId() {
        return patientId;
    }

    public Integer getProgram() {
        return program;
    }

    public Integer getLevel() {
        return level;
    }

    public Boolean getVisibilityForFamily() {
        return visibilityForFamily;
    }

    public List<MappedTrainingResultDTO> getResults() {
        return results;
    }
}
