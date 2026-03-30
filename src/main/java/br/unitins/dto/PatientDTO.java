package br.unitins.dto;

import java.time.OffsetDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import br.unitins.model.Patient;
import br.unitins.validation.AfterDate;

public class PatientDTO {
    @NotBlank(message = "Campo name é obrigatório")
    @Size(message = "Campo name deve ter o tamanho entre 4 e 30", min = 4, max = 30)
    private String name;

    @Past(message = "Campo birth deve estar no passado")
    @AfterDate(message = "Campo birth deve ser maior ou igual a 1934-01-01", referenceDate = "1934-01-01")
    private OffsetDateTime birth;

    @Past(message = "Campo treatmentStartedAt deve estar no passado")
    @AfterDate(message = "Campo treatmentStartedAt deve ser maior ou igual a 1960-01-01", referenceDate = "1960-01-01")
    private OffsetDateTime treatmentStartedAt;

    public Patient toPatient() {
        Patient patient = new Patient();

        patient.name = name;

        if (birth != null) {
            patient.birth = birth.toLocalDate();
        }

        if (treatmentStartedAt != null) {
            patient.treatmentStartedAt = treatmentStartedAt.toLocalDate();
        }

        return patient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OffsetDateTime getBirth() {
        return birth;
    }

    public void setBirth(OffsetDateTime birth) {
        this.birth = birth;
    }

    public OffsetDateTime getTreatmentStartedAt() {
        return treatmentStartedAt;
    }

    public void setTreatmentStartedAt(OffsetDateTime treatmentStartedAt) {
        this.treatmentStartedAt = treatmentStartedAt;
    }
}
