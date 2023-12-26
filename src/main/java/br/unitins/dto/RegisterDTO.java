package br.unitins.dto;

import br.unitins.model.Therapist;
import br.unitins.model.Users;
import br.unitins.model.enums.Role;
import br.unitins.model.enums.TrainingResult;

public class RegisterDTO {
    private String name;
    private String email;
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

    // public Therapist toFamily(String hash) {
    //     Therapist therapist = new Therapist();

    //     return therapist;
    // }

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
