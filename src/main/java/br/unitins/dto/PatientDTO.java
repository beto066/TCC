package br.unitins.dto;

import java.time.LocalDate;

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
    private LocalDate birth;

    @Past(message = "Campo treatmentStartedAt deve estar no passado")
    @AfterDate(message = "Campo treatmentStartedAt deve ser maior ou igual a 1960-01-01", referenceDate = "1960-01-01")
    private LocalDate treatmentStartedAt;

    public Patient toPatient() {
        Patient patient = new Patient();

        patient.name = name;
        patient.birth = birth;
        patient.treatmentStartedAt = treatmentStartedAt;

        return patient;
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
