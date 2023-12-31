package br.unitins.dto;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;

import br.unitins.model.Therapist;
import br.unitins.model.Users;
import br.unitins.model.enums.Role;
import br.unitins.model.enums.TrainingResult;
import br.unitins.service.EmailService;

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

    public boolean validate() {
        if (name == null || name.trim().length() < 4 || name.trim().length() > 30) {
            throw new WebApplicationException(
                "The field name is required with a size less than 30 and most than 4",
                422
            );
        }
        if (email == null || !EmailService.isEmail(email.trim())) {
            throw new WebApplicationException(
                "the field email must be an email",
                422
            );
        }
        if (password == null || password.trim().length() < 6) {
            throw new WebApplicationException(
                "the field password is required with a size most than 6",
                422
            );
        }
        if (type == null || Role.valueOf(type) == null) {
            throw new NotFoundException();
        }
        return true;
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
