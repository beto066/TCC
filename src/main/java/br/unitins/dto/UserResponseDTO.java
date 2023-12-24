package br.unitins.dto;

import br.unitins.model.Users;

public class UserResponseDTO {
    private Long id;
    private String name;
    private String email;
    // private String codArea;
    // private String telefone;
    private String password;
    private String imageName;

    public UserResponseDTO(Users user) {
        this.id = user.id;
        this.name = user.name;
        this.email = user.email;
        this.password = user.password;
        // this.codArea = user.codArea;
        // this.telefone = user.telefone;
        this.imageName = user.imageName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    // public String getTelefone() {
    //     return telefone;
    // }
    // public void setTelefone(String telefone) {
    //     this.telefone = telefone;
    // }

    // public String getCodArea() {
    //     return codArea;
    // }
    // public void setCodArea(String codArea) {
    //     this.codArea = codArea;
    // }
}
