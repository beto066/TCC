package br.unitins.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import br.unitins.model.Family;
import br.unitins.model.Therapist;
import br.unitins.model.Users;
import br.unitins.model.enums.Role;
import br.unitins.model.enums.TrainingResult;

public class RegisterDTO {
    @Size(message = "Campo name deve ser entre 4 e 30", min = 4, max = 30)
    private String name;

    @Email(message = "Insira um email válido")
    private String email;

    @Size(min = 6, max = 30)
    private String password;
    private Integer type;

    public Users toUser(String hash) {
        Users user = new Users();
        user.name = this.name;
        user.email = this.email;
        user.password = hash;
        return user;
    }

    public Therapist toTherapist(String hash) {
        Therapist therapist = new Therapist();
        therapist.name = this.name;
        therapist.email = this.email;
        therapist.password = hash;
        therapist.roles.add(Role.THERAPIST);
        therapist.tableValues = TrainingResult.toNoteTableValues(therapist);

        return therapist;
    }

    public Family toFamily(String hash) {
        Family family = new Family();
        family.name = this.name;
        family.email = this.email;
        family.password = hash;
        family.roles.add(Role.FAMILY);

        return family;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
