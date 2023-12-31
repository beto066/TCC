package br.unitins.dto;

import java.time.LocalDate;

import javax.ws.rs.WebApplicationException;

import br.unitins.model.Patient;

public class PatientDTO {
    private String name;
    private LocalDate birth;
    private LocalDate treatmentStartedAt;

    public Patient toPatient() {
        Patient patient = new Patient();

        patient.name = name;
        patient.birth = birth;
        patient.treatmentStartedAt = treatmentStartedAt;

        return patient;
    }

    public boolean validate() {
        if (name == null || name.length() < 4 || name.length() > 30) {
            throw new WebApplicationException(
                "The field name is required with a size less than 30 and most than 4",
                422
            );
        }

        if (
            birth == null ||
            birth.isBefore(LocalDate.of(1934, 1, 1)) ||
            birth.isAfter(LocalDate.now())
        ) {
            throw new WebApplicationException(
                "birth is required and cannot be greater than the current date or less than 01/01/1934",
                422
            );
        }

        if (treatmentStartedAt != null && (
            treatmentStartedAt.isBefore(LocalDate.of(1960, 1, 1)) ||
            treatmentStartedAt.isAfter(LocalDate.now())
        )) {
            throw new WebApplicationException(
                "treatmentStartedAt is required and cannot be greater than the current date or less than 01/01/1934",
                422
            );
        }

        return true;
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

    public LocalDate getTreatmentStartedAt() {
        return treatmentStartedAt;
    }

    public void setTreatmentStartedAt(LocalDate treatmentStartedAt) {
        this.treatmentStartedAt = treatmentStartedAt;
    }
}
