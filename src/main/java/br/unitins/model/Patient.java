package br.unitins.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Patient extends DefaultEntity {
    public String name;

    @OneToMany(
        mappedBy = "patient",
        cascade = CascadeType.ALL
    )
    public List<Note> notes;
}
