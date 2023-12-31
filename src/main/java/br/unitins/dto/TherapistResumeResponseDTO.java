package br.unitins.dto;

import br.unitins.model.Therapist;

public class TherapistResumeResponseDTO {
    private Long id;
    private String name;
    private String imageName;

    public TherapistResumeResponseDTO(Therapist therapist) {
        id = therapist.id;
        name = therapist.name;
        imageName = therapist.imageName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
