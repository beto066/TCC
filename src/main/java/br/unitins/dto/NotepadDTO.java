package br.unitins.dto;

import java.util.ArrayList;

import jakarta.validation.constraints.Size;

public class NotepadDTO {
    private Long patientId;
    private Integer program;
    private Integer level;
    private Boolean visibilityForFamily;
    private ArrayList<String> body;

    @Size(message = "O campo title deve ter o tamanho entre 2 e 10", min = 2, max = 10)
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getVisibilityForFamily() {
        return visibilityForFamily;
    }

    public void setVisibilityForFamily(Boolean visibilityForFamily) {
        this.visibilityForFamily = visibilityForFamily;
    }

    public ArrayList<String> getBody() {
        return body;
    }

    public void setBody(ArrayList<String> body) {
        this.body = body;
    }
}
