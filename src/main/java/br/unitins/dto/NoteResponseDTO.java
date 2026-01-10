package br.unitins.dto;

import java.time.LocalDateTime;

import br.unitins.model.Note;

public abstract class NoteResponseDTO {
    private Long id;
    private PatientResponseDTO patient;
    private Long authorId;
    private String program;
    private String type;
    private String level;
    private Boolean visibilityForFamily;
    private LocalDateTime createdAt;

    public NoteResponseDTO(Note note) {
        patient = new PatientResponseDTO(note.patient);
        if (note.id != null) {
            id = note.id;
        }
        if (note.program != null) {
            program = note.program.getLabel();
        }
        if (note.type != null) {
            type = note.type.getLabel();
        }
        if (note.level != null) {
            level = note.level.getLabel();
        }
        if (note.visibilityForFamily != null) {
            visibilityForFamily = note.visibilityForFamily;
        }
        if (note.createdAt != null) {
            createdAt = note.createdAt;
        }
        if (note.author != null && note.author.id != null) {
            authorId = note.author.id;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientResponseDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientResponseDTO patient) {
        this.patient = patient;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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

    public Boolean isVisibilityForFamily() {
        return visibilityForFamily;
    }

    public void setVisibilityForFamily(Boolean visibilityForFamily) {
        this.visibilityForFamily = visibilityForFamily;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
