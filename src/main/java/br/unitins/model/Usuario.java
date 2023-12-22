package br.unitins.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;

@Entity
public class Usuario extends DefaultEntity {
  
    public String nome; 
    public String email;
    public String senha;
    public String nomeImagem;

    // @OneToOne(cascade=CascadeType.ALL)
    // @JoinColumn(name = "id_telefone")
    // public Telefone teLefone;

    @ElementCollection
    @CollectionTable(name = "Roles", 
    joinColumns = @JoinColumn(name= "id_usuario", referencedColumnName = "id"))
    @Column(name = "role", length = 50)
    public Set<Role> roles = new HashSet<>();
    
    // @ManyToOne
    // @JoinColumn(name = "id_usuario")
    // public Usuario usuario;


    public String telefone;
    public String codArea;
}
