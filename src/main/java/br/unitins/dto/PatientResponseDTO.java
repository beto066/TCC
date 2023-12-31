package br.unitins.dto;

import java.time.LocalDate;

import br.unitins.model.Patient;

public class PatientResponseDTO {
    private Long id;
    private String name;
    private LocalDate birth;
    private String imageName;
    private LocalDate treatmentStartedAt;

    public PatientResponseDTO(Patient patient) {
        this.id = patient.id;
        this.name = patient.name;
        this.birth = patient.birth;
        this.imageName = patient.imageName;
        this.treatmentStartedAt = patient.treatmentStartedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public LocalDate getTreatmentStartedAt() {
        return treatmentStartedAt;
    }

    public void setTreatmentStartedAt(LocalDate treatmentStartedAt) {
        this.treatmentStartedAt = treatmentStartedAt;
    }
}
