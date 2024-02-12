package br.unitins.dto;

import java.util.ArrayList;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class NotepadDTO {
    private Long patientId;
    private Integer program;
    private Integer level;
    private Boolean visibilityForFamily;

    @Valid
    @Min(message = "Campo title não pode ser menor que 2", value = 2)
    @Max(message = "Campo title não pode ser menor que 20", value = 30)
    private ArrayList<String> body;

    @Min(message = "Campo title não pode ser menor que 2", value = 2)
    @Max(message = "Campo title não pode ser menor que 20", value = 10)
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
