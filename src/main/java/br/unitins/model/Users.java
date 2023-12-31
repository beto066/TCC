package br.unitins.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import br.unitins.model.enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Users extends DefaultEntity {
    public String name;
    public String email;
    public String password;
    public String imageName;

    @OneToMany(
        mappedBy = "author",
        cascade = CascadeType.ALL
    )
    public List<Note> notes;

    @ElementCollection
    @CollectionTable(
        name = "Roles",
        joinColumns = @JoinColumn(name= "user_id", referencedColumnName = "id")
    )
    @Column(name = "role", length = 50)
    public Set<Role> roles = new HashSet<>();
}
