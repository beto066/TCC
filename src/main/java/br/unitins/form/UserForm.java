package br.unitins.form;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

public class UserForm {
    @FormParam("id")
    private Long id;

    @FormParam("name")
    private String name;

    @FormParam("email")
    private String email;

    @FormParam("password")
    private String password;

    @FormParam("image")
    @PartType("application/octet-stream")
    private byte[] image;

    @FormParam("imageName")
    private String imageName;

    public byte[] getImagem() {
        return image;
    }

    public void setImagem(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}