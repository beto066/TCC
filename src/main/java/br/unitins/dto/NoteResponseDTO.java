package br.unitins.dto;

import br.unitins.model.Note;

public abstract class NoteResponseDTO {
    private PatientResponseDTO patient;
    private String program;
    private String type;
    private String level;

    public NoteResponseDTO(Note note) {
        patient = new PatientResponseDTO(note.patient);
        if (note.program != null) {
            program = note.program.getLabel();
        }
        if (note.type != null) {
            type = note.type.getLabel();
        }
        if (note.level != null) {
            level = note.level.getLabel();
        }
    }

    public PatientResponseDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientResponseDTO patient) {
        this.patient = patient;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
