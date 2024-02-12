package br.unitins.dto;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;

import br.unitins.model.Patient;
import br.unitins.validation.AfterDate;

public class PatientDTO {
    @NotBlank(message = "Campo name é obrigatório")
    @Min(message = "Campo name não pode ser menor que 4", value = 4)
    @Max(message = "Campo nome não pode ser maior que 30", value = 30)
    private String name;

    @Past(message = "Campo birth deve estar no passado")
    @AfterDate(referenceDate = "1934-01-01")
    private LocalDate birth;

    @Past(message = "Campo treatmentStartedAt deve estar no passado")
    @AfterDate(referenceDate = "1960-01-01")
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
