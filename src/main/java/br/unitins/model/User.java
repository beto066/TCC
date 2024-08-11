package br.unitins.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import br.unitins.model.enums.Role;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User extends DefaultEntity {
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
