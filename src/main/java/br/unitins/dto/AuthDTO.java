package br.unitins.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;

public class AuthDTO {

    @Email(message = "Insira um email válido")
    private String email;

    @Min(message = "O campo senha deve ser maior ou igual à 6", value = 6)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}