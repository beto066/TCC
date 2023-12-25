package br.unitins.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users extends DefaultEntity {
    public String name; 
    public String email;
    public String password;
    public String imageName;

    @ElementCollection
    @CollectionTable(name = "Roles", 
    joinColumns = @JoinColumn(name= "id_user", referencedColumnName = "id"))
    @Column(name = "role", length = 50)
    public Set<Role> roles = new HashSet<>();
}
